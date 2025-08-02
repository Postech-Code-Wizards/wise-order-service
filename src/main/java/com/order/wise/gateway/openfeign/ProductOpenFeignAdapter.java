package com.order.wise.gateway.openfeign;

import com.order.wise.domain.Product;
import com.order.wise.gateway.ProductGateway;
import com.order.wise.gateway.openfeign.converter.GraphQLResponseToProduct;
import com.order.wise.gateway.openfeign.request.GraphQLRequest;
import com.order.wise.gateway.openfeign.response.GraphQLResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductOpenFeignAdapter implements ProductGateway {

    private final ProductOpenFeignClient productOpenFeignClient;
    private final GraphQLResponseToProduct graphQLResponseToProduct;

    @Override
    public Product getProductBySKU(String sku) {

        String query = "query FindProductBySku($sku: String!) { findProductBySku(sku: $sku) { id name description category price sku } }";
        Map<String, Object> variables = new HashMap<>();
        variables.put("sku", sku);

        GraphQLRequest request = new GraphQLRequest(query,
                variables,
                "FindProductBySku"
        );
        GraphQLResponse graphQLResponse = productOpenFeignClient.execute(request);
        return graphQLResponseToProduct.convert(graphQLResponse);
    }
}
