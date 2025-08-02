package com.order.wise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class Product {

    private BigInteger id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;

}