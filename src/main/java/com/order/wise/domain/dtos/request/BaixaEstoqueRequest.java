package com.order.wise.domain.dtos.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class BaixaEstoqueRequest {

    private Long itemId;
    private Integer quantidade;
    private Long pedidoId;

}
