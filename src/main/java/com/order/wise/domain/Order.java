package com.order.wise.domain;

import com.order.wise.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {
    private BigInteger id;
    private BigInteger clientId;
    private String clientIdentifier;
    private ZonedDateTime dateCreated;
    private StatusEnum status;
    private String creditCardNumber;
    private String paymentId;
    private BigDecimal totalValue;
    private List<OrderItem> orderItems;
}