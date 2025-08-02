package com.order.wise.infrastructure.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderDTO {
    private ClientDTO client;
    private List<ProductDTO> productList;
    private PaymentMethodDTO paymentMethod;
}