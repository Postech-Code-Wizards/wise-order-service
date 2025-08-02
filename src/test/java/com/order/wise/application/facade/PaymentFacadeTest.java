package com.order.wise.application.facade;

import com.order.wise.application.usecase.payment.ProcessCallbackPaymentUseCase;
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
class PaymentFacadeTest {

    @Mock
    private ProcessCallbackPaymentUseCase processCallbackPaymentUseCase;

    @InjectMocks
    private PaymentFacade paymentFacade;

    @Test
    @DisplayName("Should call processCallbackPaymentUseCase.execute with payment ID and success status")
    void paymentResponseTest() {
        CallbackPaymentDTO callbackPaymentDTO = Instancio.create(CallbackPaymentDTO.class);

        paymentFacade.paymentResponse(callbackPaymentDTO);

        verify(processCallbackPaymentUseCase).execute(callbackPaymentDTO.getPaymentId(), callbackPaymentDTO.isSuccess());
    }
}