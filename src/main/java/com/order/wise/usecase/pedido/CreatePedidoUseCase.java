package com.order.wise.usecase.pedido;


import com.order.wise.domain.ItensPedidos;
import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;
import com.order.wise.gateway.PedidoGateway;
import com.order.wise.usecase.itenspedido.CalcularSubtotalUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreatePedidoUseCase {
    private final PedidoGateway pedidoGateway;
    private final CalcularSubtotalUseCase calcularSubtotalUseCase;
    private final CalcularValorTotalPedidoUseCase calcularValorTotalPedidoUseCase;


    //Função responsavel pela criação do pedido na tabela
    public Pedido createPedido(Pedido pedido) {
            List<ItensPedidos> itensPedidos = pedido.getItensPedidos();
            itensPedidos = calcularSubtotalUseCase.calcladoraSubtotal(itensPedidos);
            pedido.setItensPedidos(itensPedidos);
            pedido.setStatus(Status.ABERTO);
            pedido.setValorTotal(calcularValorTotalPedidoUseCase.calcularValorTotal(itensPedidos));
            pedidoGateway.save(pedido);

            return pedido;
    }


}
