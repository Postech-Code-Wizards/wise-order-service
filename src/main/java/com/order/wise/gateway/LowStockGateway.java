package com.order.wise.gateway;

import com.order.wise.gateway.messaging.rabbitMQ.dto.StockDTO;

import java.util.List;

public interface LowStockGateway {

    void send(List<StockDTO> stockDTO);

}
