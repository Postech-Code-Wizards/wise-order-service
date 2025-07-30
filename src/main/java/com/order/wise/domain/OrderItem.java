package com.order.wise.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class OrderItem {
    private BigInteger id;
    private Order order;
    private BigInteger productId;
    private String skuProduct;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    
    public BigDecimal calculateSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}