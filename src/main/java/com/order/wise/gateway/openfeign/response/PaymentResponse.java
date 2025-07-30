package com.order.wise.gateway.openfeign.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResponse {
    private String paymentId;
    private String status;
    private String message;
}