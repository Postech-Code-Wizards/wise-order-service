package com.order.wise.gateway;

import com.order.wise.gateway.messaging.rabbitMQ.dto.StockDTO;

public interface LowStockGateway {

    void send(StockDTO stockDTO);

}
