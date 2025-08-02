package com.order.wise.application.usecase.product;

import com.order.wise.domain.Order;
import com.order.wise.domain.OrderItem;
import com.order.wise.domain.Product;
import com.order.wise.exceptions.ProductNotFoundException;
import com.order.wise.gateway.OrderGateway;
import com.order.wise.gateway.OrderItemGateway;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static com.order.wise.domain.enums.StatusEnum.PRODUCT_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ProcessOrderItemProductAssignmentUseCaseTest {

    @Mock
    private FindProductsBySkuUseCase findProductsBySkuUseCase;

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private OrderItemGateway orderItemGateway;

    @InjectMocks
    private ProcessOrderItemProductAssignmentUseCase useCase;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    @DisplayName("Should enrich and save order items when matching products are found")
    void shouldEnrichAndSaveOrderItems() {
        Order order = Instancio.create(Order.class);
        OrderItem item1 = Instancio.of(OrderItem.class)
                .set(Select.field("skuProduct"), "SKU-123")
                .create();
        OrderItem item2 = Instancio.of(OrderItem.class)
                .set(Select.field("skuProduct"), "SKU-456")
                .create();
        List<OrderItem> orderItems = List.of(item1, item2);

        Product product1 = new Product(BigInteger.ONE, "PRODUCT A", "Product A", "SKU-123", new BigDecimal("10.0"));
        Product product2 = new Product(BigInteger.TWO, "PRODUCT B", "Product B", "SKU-456", new BigDecimal("12.0"));
        List<Product> products = List.of(product1, product2);

        when(findProductsBySkuUseCase.execute(List.of("SKU-123", "SKU-456"))).thenReturn(products);

        useCase.execute(order, orderItems);

        verify(findProductsBySkuUseCase).execute(List.of("SKU-123", "SKU-456"));
        verify(orderItemGateway).saveAll(anyList());
        verify(orderGateway, never()).updateStatus(any(), any());
    }

    @Test
    @DisplayName("Should update order status and throw exception when product lookup fails")
    void shouldUpdateStatusAndThrowIfProductLookupFails() {
        Order order = Instancio.create(Order.class);
        List<OrderItem> orderItems = Instancio.ofList(OrderItem.class).size(2).create();

        when(findProductsBySkuUseCase.execute(anyList())).thenThrow(new RuntimeException("Error"));

        assertThatThrownBy(() -> useCase.execute(order, orderItems))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("Product not found for order");

        verify(orderGateway).updateStatus(order.getId(), PRODUCT_NOT_FOUND);
        verify(orderItemGateway, never()).saveAll(anyList());
    }

    @Test
    @DisplayName("Should throw exception when SKU does not match any product")
    void shouldThrowWhenSkuNotMatched() {
        Order order = Instancio.create(Order.class);
        OrderItem item = Instancio.of(OrderItem.class)
                .set(Select.field("skuProduct"), "UNKNOWN-SKU")
                .create();
        List<OrderItem> orderItems = List.of(item);
        List<Product> products = List.of();

        when(findProductsBySkuUseCase.execute(List.of("UNKNOWN-SKU"))).thenReturn(products);

        assertThatThrownBy(() -> useCase.execute(order, orderItems))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("Product not found for order");

        verify(orderGateway).updateStatus(order.getId(), PRODUCT_NOT_FOUND);
        verify(orderItemGateway, never()).saveAll(anyList());
    }

    @Test
    @DisplayName("Should not call saveAll when there are no items to update")
    void shouldNotSaveIfNoItemsToUpdate() {
        Order order = Instancio.create(Order.class);
        List<OrderItem> orderItems = List.of();

        when(findProductsBySkuUseCase.execute(List.of())).thenThrow(new ProductNotFoundException("Product not found for order"));

        assertThatThrownBy(() -> useCase.execute(order, orderItems))
                .isInstanceOf(ProductNotFoundException.class);

        verify(orderGateway).updateStatus(order.getId(), PRODUCT_NOT_FOUND);
        verify(orderItemGateway, never()).saveAll(anyList());
    }
}
