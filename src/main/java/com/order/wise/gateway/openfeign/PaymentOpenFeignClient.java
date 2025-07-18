package com.order.wise.gateway.openfeign;

import com.order.wise.gateway.openfeign.request.PaymentRequest;
import com.order.wise.gateway.openfeign.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-api", url = "${payment.service.url}")
public interface PaymentOpenFeignClient {

    @GetMapping("/payment")
    PaymentResponse getPayment(@RequestBody PaymentRequest paymentRequest);

}
