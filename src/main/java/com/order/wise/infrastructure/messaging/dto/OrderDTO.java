package com.order.wise.infrastructure.messaging.dto;

import com.order.wise.domain.ItensPedidos;
import com.order.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private Long clienteId;
    private ZonedDateTime dataCriacao;
    private Status status;
    private String cartaoCredito;
    private Long pagamentoId;
    private BigDecimal valorTotal;
    private List<ItensPedidos> itensPedidos;

}
