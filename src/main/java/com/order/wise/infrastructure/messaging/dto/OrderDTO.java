package com.order.wise.infrastructure.messaging.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private BigDecimal totalPrice;
    private ClientDTO client;
    private List<ProductDTO> productList;
    private PaymentMethodDTO paymentMethod;

}