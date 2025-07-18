package com.order.wise.gateway;

import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;

public interface OrderGateway {

    Order save(Order order);

    void updateStatus(Long id, StatusEnum status);

    void updatePayment(Long id, String paymentId);

}
