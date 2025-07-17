package com.order.wise.infrastructure.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockResponseDTO {
    private Long pedidoId;
    private Boolean status;
}
