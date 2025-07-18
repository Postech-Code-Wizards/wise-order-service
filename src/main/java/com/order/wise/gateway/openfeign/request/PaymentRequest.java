package com.order.wise.gateway.openfeign.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    private String creditCard;
    private String totalValue;
}
