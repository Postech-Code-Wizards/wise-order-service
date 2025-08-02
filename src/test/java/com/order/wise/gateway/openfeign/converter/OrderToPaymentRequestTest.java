package com.order.wise.gateway.openfeign.converter;

import com.order.wise.domain.Order;
import com.order.wise.gateway.openfeign.request.PaymentRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderToPaymentRequestUnitTest {

    private final OrderToPaymentRequest orderToPaymentRequest = new OrderToPaymentRequest();

    @Test
    @DisplayName("Should convert Order domain object to PaymentRequest correctly")
    void execute_ShouldConvertOrderToPaymentRequest() {

        Order order = Instancio.create(Order.class);

        PaymentRequest paymentRequest = orderToPaymentRequest.execute(order);

        assertThat(paymentRequest).isNotNull();
        assertThat(paymentRequest.getTotalValue()).isEqualTo(order.getTotalValue().doubleValue());
        assertThat(paymentRequest.getCreditCard()).isEqualTo(order.getCreditCardNumber());
    }
}