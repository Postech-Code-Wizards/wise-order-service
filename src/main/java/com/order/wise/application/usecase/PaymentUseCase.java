package com.order.wise.application.usecase;

import com.order.wise.application.facade.converter.PedidoToPaymentDTO;
import com.order.wise.domain.Pedido;
import com.order.wise.gateway.PaymentGateway;
import com.order.wise.gateway.messaging.rabbitmq.dto.PaymentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentUseCase {

    private final PedidoToPaymentDTO pedidoToPaymentDTO;
    private final PaymentGateway paymentGateway;

    public void execute(Pedido pedido) {
        log.info("Processing payment for order: {} with amount: {}", pedido.getId(), pedido.getValorTotal());
        PaymentDTO paymentRequest = pedidoToPaymentDTO.execute(pedido);
        paymentGateway.send(paymentRequest);
    }
}
