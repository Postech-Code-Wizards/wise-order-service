package com.order.wise.application.usecase.product;

import com.order.wise.domain.Product;
import com.order.wise.gateway.ProductGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductsBySkuUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private FindProductsBySkuUseCase findProductsBySkuUseCase;

    @Test
    @DisplayName("Should return a list of products for given SKUs")
    void executeTest() {
        List<String> skuList = Instancio.ofList(String.class).size(3).create();
        List<Product> mockProducts = skuList.stream()
                .map(sku -> {
                    Product product = Instancio.create(Product.class);

                    return new Product(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            sku,
                            product.getPrice()
                    );
                })
                .toList();

        for (int i = 0; i < skuList.size(); i++) {
            when(productGateway.getProductBySKU(skuList.get(i))).thenReturn(mockProducts.get(i));
        }

        List<Product> result = findProductsBySkuUseCase.execute(skuList);

        assertEquals(skuList.size(), result.size());
        for (int i = 0; i < skuList.size(); i++) {
            assertEquals(skuList.get(i), result.get(i).getSku());
            assertEquals(mockProducts.get(i).getName(), result.get(i).getName());
            assertEquals(mockProducts.get(i).getDescription(), result.get(i).getDescription());
            assertEquals(mockProducts.get(i).getPrice(), result.get(i).getPrice());
            assertEquals(mockProducts.get(i).getId(), result.get(i).getId());
        }
    }
}