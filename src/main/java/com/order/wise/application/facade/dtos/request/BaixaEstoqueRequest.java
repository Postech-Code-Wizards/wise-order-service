package com.order.wise.application.facade.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class BaixaEstoqueRequest {

    private Long itemId;
    private Integer quantidade;
    private Long pedidoId;

}
