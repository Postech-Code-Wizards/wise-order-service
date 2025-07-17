package com.order.wise.gateway.messaging.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private BigDecimal totalValue;
    private String creditCardNumber;
    private Long orderId;
    private Long clientId;

}
