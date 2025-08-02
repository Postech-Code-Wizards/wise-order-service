package com.order.wise.infrastructure.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {
    private String sku;
    private Integer quantity;
}