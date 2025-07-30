package com.order.wise.application.usecase.product;

import com.order.wise.domain.Order;
import com.order.wise.domain.OrderItem;
import com.order.wise.domain.Product;
import com.order.wise.exceptions.ProductNotFoundException;
import com.order.wise.gateway.OrderGateway;
import com.order.wise.gateway.OrderItemGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.order.wise.domain.enums.StatusEnum.PRODUCT_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessOrderItemProductAssignmentUseCase {

    private final FindProductsBySkuUseCase findProductsBySkuUseCase;
    private final OrderGateway orderGateway;
    private final OrderItemGateway orderItemGateway;

    public void execute(Order order, List<OrderItem> orderItemList) {

        List<String> skuList = listProductsSku(orderItemList);
        List<OrderItem> orderItemListToSave = new ArrayList<>();
        try {
            List<Product> products = findProductsBySkuUseCase.execute(skuList);
            orderItemList.forEach(orderItem -> enrichOrderItemWithProductDetails(orderItem,
                    products,
                    orderItemListToSave
            ));
            if (!orderItemListToSave.isEmpty()) {
                orderItemGateway.saveAll(orderItemListToSave);
            }

        } catch (Exception e) {
            orderGateway.updateStatus(order.getId(), PRODUCT_NOT_FOUND);
            log.error("Error finding product for order: {}", order, e);
            throw new ProductNotFoundException("Product not found for order: " + order.getId(), e);
        }

    }

    private static void enrichOrderItemWithProductDetails(OrderItem orderItem, List<Product> products, List<OrderItem> orderItemListToSave) {
        Product product = products.stream()
                .filter(p -> p.getSku().equals(orderItem.getSkuProduct()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found for SKU: " + orderItem.getSkuProduct()));

        orderItemListToSave.add(
                new OrderItem(
                        orderItem.getId(),
                        orderItem.getOrder(),
                        product.getId(),
                        product.getSku(),
                        product.getName(),
                        orderItem.getQuantity(),
                        product.getPrice()
                )
        );
    }

    private static List<String> listProductsSku(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(OrderItem::getSkuProduct)
                .toList();
    }

}
