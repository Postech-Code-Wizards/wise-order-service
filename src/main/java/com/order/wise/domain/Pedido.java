package com.order.wise.domain;

import com.order.wise.domain.enums.Status;
import com.order.wise.gateway.database.entities.ItensPedidosEntity;
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
