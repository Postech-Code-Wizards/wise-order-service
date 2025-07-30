package com.order.wise.gateway;

import com.order.wise.gateway.rabbitmq.dto.StockDTO;

import java.util.List;

public interface StockGateway {

    void low(List<StockDTO> stockDTO);

    void reset(List<StockDTO> stockDTO);

}
