package com.order.wise.gateway.openfeign;

import com.order.wise.gateway.openfeign.request.GraphQLRequest;
import com.order.wise.gateway.openfeign.response.GraphQLResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-api", url = "${product.service.url}")
public interface ProductOpenFeignClient {

    @PostMapping(path = "/graphql", consumes = "application/json", produces = "application/json")
    GraphQLResponse execute(@RequestBody GraphQLRequest request);

}