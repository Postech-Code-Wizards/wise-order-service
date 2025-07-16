package com.order.wise.application.facade.converter;

import com.order.wise.domain.Pedido;
import com.order.wise.gateway.messaging.rabbitmq.dto.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class PedidoToPaymentDTO {

    public PaymentDTO execute(Pedido pedido) {
        return PaymentDTO.builder()
                .valorTotal(pedido.getValorTotal())
                .pedidoId(pedido.getId())
                .clienteId(pedido.getClienteId())
                .dadosPagamento(pedido.getCartaoCredito())
                .build();

    }
}
