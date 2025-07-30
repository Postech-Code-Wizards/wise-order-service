package com.order.wise.application.facade;

import com.order.wise.application.usecase.payment.ProcessCallbackPaymentUseCase;
import com.order.wise.infrastructure.rabbitmq.dto.CallbackPaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFacade {

    private final ProcessCallbackPaymentUseCase processCallbackPaymentUseCase;

    public void paymentResponse(CallbackPaymentDTO callbackPaymentDTO) {
        processCallbackPaymentUseCase.execute(callbackPaymentDTO.getPaymentId(), callbackPaymentDTO.isSuccess());
    }
}
