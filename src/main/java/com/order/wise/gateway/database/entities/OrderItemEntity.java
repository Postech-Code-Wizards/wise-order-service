package com.order.wise.gateway.database.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "tb_order_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private OrderEntity orderEntity;

    @Column(name = "product_id")
    private BigInteger productId;

    @Column(name = "sku_product", length = 50)
    private String skuProduct;

    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @DecimalMin(value = "0.00", inclusive = false)
    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

}
