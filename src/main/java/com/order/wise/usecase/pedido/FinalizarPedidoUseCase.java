package com.order.wise.usecase.pedido;

import com.order.wise.domain.Pedido;
import com.order.wise.gateway.PedidoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinalizarPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    public void salvarPedido(Pedido pedido) {

        pedidoGateway.updateFinalizarPedido(pedido);

    }

}
