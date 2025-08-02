package com.order.wise.infrastructure.rabbitmq.dto;

import com.order.wise.domain.enums.PaymentMethodTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentMethodDTO {
    private PaymentMethodTypeEnum paymentMethodTypeEnum;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardCvv;
}
