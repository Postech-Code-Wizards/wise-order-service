package com.order.wise.infrastructure.rabbitmq;

import com.order.wise.application.facade.PaymentFacade;
import com.order.wise.infrastructure.rabbitmq.configuration.RabbitMQConfig;
import com.order.wise.infrastructure.rabbitmq.dto.CallbackPaymentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallbackPaymentConsumer {

    private final PaymentFacade paymentFacade;

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_QUEUE_NAME)
    public void execute(CallbackPaymentDTO callbackPaymentDTO) {
        log.info("Received payment response message: {}", callbackPaymentDTO.toString());
        paymentFacade.paymentResponse(callbackPaymentDTO);
    }
}
