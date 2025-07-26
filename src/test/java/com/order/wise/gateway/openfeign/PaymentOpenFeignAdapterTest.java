package com.order.wise.gateway.openfeign;

import com.order.wise.domain.Order;
import com.order.wise.gateway.openfeign.converter.OrderToPaymentRequest;
import com.order.wise.gateway.openfeign.request.PaymentRequest;
import com.order.wise.gateway.openfeign.response.PaymentResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentOpenFeignAdapterUnitTest {

    @Mock
    private PaymentOpenFeignClient paymentOpenFeignClient;

    @Mock
    private OrderToPaymentRequest orderToPaymentRequest;

    @InjectMocks
    private PaymentOpenFeignAdapter paymentOpenFeignAdapter;

    @Test
    @DisplayName("Should successfully get payment ID when payment client returns a valid response")
    void getPayment_ShouldReturnPaymentIdOnSuccess() {

        Order order = Instancio.create(Order.class);
        PaymentRequest paymentRequest = Instancio.create(PaymentRequest.class);
        PaymentResponse paymentResponse = Instancio.create(PaymentResponse.class);

        when(orderToPaymentRequest.execute(order)).thenReturn(paymentRequest);
        when(paymentOpenFeignClient.getPayment(paymentRequest)).thenReturn(paymentResponse);

        String resultPaymentId = paymentOpenFeignAdapter.getPayment(order);

        assertThat(resultPaymentId).isEqualTo(paymentResponse.getPaymentId());
        verify(orderToPaymentRequest).execute(order);
        verify(paymentOpenFeignClient).getPayment(paymentRequest);
    }

}