package com.order.wise.application.usecase.pedido;

import com.order.wise.domain.enums.Status;
import com.order.wise.gateway.PedidoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateStatusPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    public void updateStatusPedido(Long idPedido, Status status, Long idPagamento) {

        pedidoGateway.updateStatus(idPedido, status, idPagamento);
    }
}
