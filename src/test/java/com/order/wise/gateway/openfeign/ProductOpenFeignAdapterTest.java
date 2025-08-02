package com.order.wise.gateway.openfeign;

import com.order.wise.domain.Product;
import com.order.wise.gateway.openfeign.converter.GraphQLResponseToProduct;
import com.order.wise.gateway.openfeign.request.GraphQLRequest;
import com.order.wise.gateway.openfeign.response.GraphQLResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductOpenFeignAdapterTest {

    @Mock
    private ProductOpenFeignClient productOpenFeignClient;
    @Mock
    private GraphQLResponseToProduct graphQLResponseToProduct;

    @InjectMocks
    private ProductOpenFeignAdapter productOpenFeignAdapter;

    @Test
    @DisplayName("Should retrieve product by SKU using OpenFeign client and convert response")
    void getProductBySKUTest() {
        String sku = Instancio.create(String.class);
        GraphQLResponse mockGraphQLResponse = Instancio.create(GraphQLResponse.class);
        Product expectedProduct = Instancio.create(Product.class);

        when(productOpenFeignClient.execute(any(GraphQLRequest.class))).thenReturn(mockGraphQLResponse);
        when(graphQLResponseToProduct.convert(mockGraphQLResponse)).thenReturn(expectedProduct);

        Product resultProduct = productOpenFeignAdapter.getProductBySKU(sku);

        ArgumentCaptor<GraphQLRequest> requestCaptor = ArgumentCaptor.forClass(GraphQLRequest.class);
        verify(productOpenFeignClient).execute(requestCaptor.capture());
        verify(graphQLResponseToProduct).convert(mockGraphQLResponse);

        GraphQLRequest capturedRequest = requestCaptor.getValue();

        // Verify the content of the captured GraphQLRequest
        String expectedQuery = "query FindProductBySku($sku: String!) { findProductBySku(sku: $sku) { id name description category price sku } }";
        Map<String, Object> expectedVariables = new HashMap<>();
        expectedVariables.put("sku", sku);
        String expectedOperationName = "FindProductBySku";

        assertNotNull(capturedRequest);
        assertEquals(expectedQuery, capturedRequest.getQuery());
        assertEquals(expectedVariables, capturedRequest.getVariables());
        assertEquals(expectedOperationName, capturedRequest.getOperationName());

        assertEquals(expectedProduct, resultProduct);
    }
}