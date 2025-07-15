package com.order.wise.gateway.messaging.rabbitMQ.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private BigDecimal valorTotal;
    private String dadosPagamento;
    private Long pedidoId;
    private Long clienteId;

}
