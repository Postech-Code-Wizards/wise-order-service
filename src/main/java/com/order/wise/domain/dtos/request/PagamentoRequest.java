package com.order.wise.domain.dtos.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class PagamentoRequest {

    private BigDecimal valorTotal;
    private String dadosPagamento;
    private Long pedidoId;
    private Long clienteId;

}
