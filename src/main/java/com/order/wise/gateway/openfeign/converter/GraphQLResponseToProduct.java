package com.order.wise.gateway.openfeign.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.wise.domain.Product;
import com.order.wise.gateway.openfeign.response.GraphQLResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
public class GraphQLResponseToProduct {

    private final ObjectMapper objectMapper;

    public Product convert(GraphQLResponse graphQLResponse) {

        @SuppressWarnings("unchecked")
        LinkedHashMap<String, Object> productMap = (LinkedHashMap<String, Object>) graphQLResponse.getData().get("findProductBySku");
        return objectMapper.convertValue(productMap, Product.class);
    }

}
