package com.order.wise.domain;

import com.order.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class Pedido {

    private Long id;
    private Long clienteId;
    private ZonedDateTime dataCriacao;
    private Status status;
    private String cartaoCredito;
    private Long pagamentoId;
    private BigDecimal valorTotal;
    private List<ItensPedidos> itensPedidos;

}
