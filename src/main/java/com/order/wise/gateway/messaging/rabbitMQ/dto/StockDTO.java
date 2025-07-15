package com.order.wise.gateway.messaging.rabbitMQ.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private Long itemId;
    private Integer quantidade;
    private Long pedidoId;

}
