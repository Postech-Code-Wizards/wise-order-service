package com.order.wise.infrastructure.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class StockResponseDTO {
    private BigInteger pedidoId;
    private Boolean status;
}