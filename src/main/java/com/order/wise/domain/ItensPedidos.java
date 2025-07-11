package com.order.wise.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItensPedidos {

    private Long id;
    private Pedido pedido_id;
    private Long produtoId;
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

}
