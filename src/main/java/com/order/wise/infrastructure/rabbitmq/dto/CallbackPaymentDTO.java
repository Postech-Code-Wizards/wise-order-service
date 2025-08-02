package com.order.wise.infrastructure.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CallbackPaymentDTO {
    String paymentId;
    boolean success;
}
