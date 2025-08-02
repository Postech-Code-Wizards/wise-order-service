package com.order.wise.application.usecase.product;

import com.order.wise.domain.Product;
import com.order.wise.gateway.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindProductsBySkuUseCase {

    private final ProductGateway productGateway;

    public List<Product> execute(List<String> skuList) {
        return skuList.stream()
                .map(sku -> {
                    Product product = productGateway.getProductBySKU(sku);
                    return new Product(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getSku(),
                            product.getPrice()
                    );
                })
                .toList();
    }
}
