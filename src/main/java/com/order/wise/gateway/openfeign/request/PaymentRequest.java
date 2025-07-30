package com.order.wise.gateway.openfeign.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentRequest {
    private String creditCard;
    private Double totalValue;
}