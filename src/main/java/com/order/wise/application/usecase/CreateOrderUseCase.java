package com.order.wise.application.usecase;

import com.order.wise.application.usecase.itenspedido.CalcularSubtotalItemUseCase;
import com.order.wise.application.usecase.pedido.CalcularValorTotalPedidoUseCase;
import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;
import com.order.wise.gateway.PedidoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateOrderUseCase {

    private final PedidoGateway pedidoGateway;
    private final CalcularSubtotalItemUseCase calcularSubtotalItemUseCase;
    private final CalcularValorTotalPedidoUseCase calcularValorTotalPedidoUseCase;

    public Pedido createOrder(Pedido pedido) {

        log.info("Creating order: {}", pedido);

        pedido.setItensPedidos(calcularSubtotalItemUseCase.calcularSubtotalItens(pedido.getItensPedidos()));
        pedido.setValorTotal(calcularValorTotalPedidoUseCase.calcularValorTotal(pedido.getItensPedidos()));
        pedido.setStatus(Status.ABERTO);
        pedidoGateway.save(pedido);
        return pedido;
    }
}
