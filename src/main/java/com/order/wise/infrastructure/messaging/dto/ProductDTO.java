package com.order.wise.infrastructure.messaging.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalPriceProduct;
}
