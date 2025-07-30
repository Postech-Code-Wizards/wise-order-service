package com.order.wise.gateway.openfeign.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.wise.domain.Product;
import com.order.wise.gateway.openfeign.response.GraphQLResponse;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GraphQLResponseToProductTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private GraphQLResponseToProduct graphQLResponseToProduct;

    @Test
    @DisplayName("Should correctly convert GraphQLResponse to Product domain object")
    void convertTest() {

        Product expectedProduct = Instancio.create(Product.class);

        LinkedHashMap<String, Object> productDataMap = new LinkedHashMap<>();
        productDataMap.put("id", expectedProduct.getId());
        productDataMap.put("name", expectedProduct.getName());
        productDataMap.put("description", expectedProduct.getDescription());
        productDataMap.put("price", expectedProduct.getPrice());
        productDataMap.put("sku", expectedProduct.getSku());

        // Prepare the GraphQLResponse object
        Map<String, Object> dataMap = Collections.singletonMap("findProductBySku", productDataMap);
        GraphQLResponse graphQLResponse = Instancio.of(GraphQLResponse.class)
                .set(Select.field("data"), dataMap)
                .create();

        when(objectMapper.convertValue(productDataMap, Product.class))
                .thenReturn(expectedProduct);

        Product actualProduct = graphQLResponseToProduct.convert(graphQLResponse);

        verify(objectMapper).convertValue(productDataMap, Product.class);
        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
    }
}