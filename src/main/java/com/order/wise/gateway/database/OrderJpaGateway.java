package com.order.wise.gateway.database;

import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.exceptions.OrderNotFoundException;
import com.order.wise.gateway.OrderGateway;
import com.order.wise.gateway.database.converter.OrderConverter;
import com.order.wise.gateway.database.entities.OrderEntity;
import com.order.wise.gateway.database.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
@RequiredArgsConstructor
public class OrderJpaGateway implements OrderGateway {

    private static final String ORDER_NOT_FOUND_WITH_ID = "Order not found with ID: ";
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Override
    public Order save(Order order) {

        OrderEntity orderEntity = orderConverter.toEntity(order);
        return orderConverter.toDomain(orderRepository.save(orderEntity));
    }

    @Override
    public void updateStatus(BigInteger id, StatusEnum status) {

        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_WITH_ID + id));

        orderEntity.setStatus(status);
        orderRepository.save(orderEntity);
    }

    @Override
    public void updatePayment(BigInteger id, String paymentId) {

        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_WITH_ID + id));

        orderEntity.setPaymentId(paymentId);
        orderRepository.save(orderEntity);
    }

    @Override
    public Order findById (BigInteger id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(ORDER_NOT_FOUND_WITH_ID + id));
        return orderConverter.toDomain(orderEntity);
    }

    @Override
    public Order findByPaymentId(String paymentId) {
        OrderEntity orderEntity = orderRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with payment ID: " + paymentId));
        return orderConverter.toDomain(orderEntity);
    }

    @Override
    public void updateClientId(BigInteger orderId, BigInteger clientId) {
        orderRepository.updateClientId(orderId, clientId);
    }

    @Override
    public void updateTotalValue(BigInteger orderId, BigDecimal totalValue) {
        orderRepository.updateTotalValue(orderId, totalValue);
    }
}
