package com.order.wise.gateway;

import com.order.wise.domain.OrderItem;

import java.util.List;

public interface OrderItemGateway {

    void saveAll(List<OrderItem> orderItemList);

}
