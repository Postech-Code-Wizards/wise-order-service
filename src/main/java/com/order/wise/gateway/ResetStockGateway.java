package com.order.wise.gateway;

import com.order.wise.gateway.messaging.rabbitmq.dto.StockDTO;

import java.util.List;

public interface ResetStockGateway {

    void send(List<StockDTO> stockDTO);
}
