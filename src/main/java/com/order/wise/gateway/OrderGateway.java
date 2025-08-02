package com.order.wise.gateway;

import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface OrderGateway {

    Order save(Order order);

    void updateStatus(BigInteger id, StatusEnum status);

    void updatePayment(BigInteger id, String paymentId);

    Order findById(BigInteger id);

    Order findByPaymentId(String paymentId);

    void updateClientId(BigInteger orderId, BigInteger clientId);

    void updateTotalValue(BigInteger orderId, BigDecimal totalValue);

}
