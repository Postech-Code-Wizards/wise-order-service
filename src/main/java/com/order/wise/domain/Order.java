package com.order.wise.domain;

import com.order.wise.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class Order {

    private Long id;
    private Long clientId;
    private ZonedDateTime dateCreated;
    private StatusEnum status;
    private String creditCardNumber;
    private String paymentId;
    private BigDecimal totalValue;
    private List<OrderItem> orderItems;

}
