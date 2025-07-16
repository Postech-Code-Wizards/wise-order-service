package com.order.wise.infrastructure.messaging.dto;

import com.order.wise.domain.enums.PaymentMethodTypeEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO {
    private PaymentMethodTypeEnum paymentMethodTypeEnum;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardCvv;
}
