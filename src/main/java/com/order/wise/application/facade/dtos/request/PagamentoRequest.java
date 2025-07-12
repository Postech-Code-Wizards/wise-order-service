package com.order.wise.application.facade.dtos.request;

import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PagamentoRequest {

    private BigDecimal valorTotal;
    private String dadosPagamento;
    private Long pedidoId;
    private Long clienteId;

}
