package com.order.wise.gateway.openfeign;

import com.order.wise.domain.Order;
import com.order.wise.gateway.PaymentGateway;
import com.order.wise.gateway.openfeign.converter.OrderToPaymentRequest;
import com.order.wise.gateway.openfeign.request.PaymentRequest;
import com.order.wise.gateway.openfeign.response.PaymentResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentOpenFeignAdapter implements PaymentGateway {

    private final PaymentOpenFeignClient paymentOpenFeignClient;
    private final OrderToPaymentRequest orderToPaymentRequest;

    @Override
    public String getPayment(Order order){

        try {
            PaymentRequest paymentRequest = orderToPaymentRequest.execute(order);
            PaymentResponse paymentResponse = paymentOpenFeignClient.getPayment(paymentRequest);
            return paymentResponse.getPaymentId();
        } catch (FeignException.NotFound e){
            log.error("Error sending payment: {}", e.getMessage());
            return null;
        }
    }

}
