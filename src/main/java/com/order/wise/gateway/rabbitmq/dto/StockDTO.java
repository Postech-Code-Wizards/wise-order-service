package com.order.wise.gateway.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class StockDTO {

    private BigInteger produtoId;
    private Integer quantidade;
    private BigInteger pedidoId;

}