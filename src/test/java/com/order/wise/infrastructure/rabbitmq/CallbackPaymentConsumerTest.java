package com.order.wise.infrastructure.rabbitmq;

import com.order.wise.application.facade.PaymentFacade;
import com.order.wise.infrastructure.rabbitmq.dto.CallbackPaymentDTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CallbackPaymentConsumerTest {

    @Mock
    private PaymentFacade paymentFacade;

    @InjectMocks
    private CallbackPaymentConsumer callbackPaymentConsumer;

    @Test
    @DisplayName("Should process received payment callback DTO and call payment facade")
    void executeTest() {
        CallbackPaymentDTO callbackPaymentDTO = Instancio.create(CallbackPaymentDTO.class);

        callbackPaymentConsumer.execute(callbackPaymentDTO);

        verify(paymentFacade).paymentResponse(callbackPaymentDTO);
    }
}