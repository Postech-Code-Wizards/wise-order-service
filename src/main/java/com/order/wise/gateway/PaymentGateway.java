package com.order.wise.gateway;

import com.order.wise.domain.Order;

public interface PaymentGateway {

    String getPayment(Order order);

}
