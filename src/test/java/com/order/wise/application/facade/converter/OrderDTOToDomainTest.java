package com.order.wise.application.facade.converter;

import com.order.wise.domain.Order;
import com.order.wise.domain.OrderItem;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.infrastructure.rabbitmq.dto.OrderDTO;
import com.order.wise.infrastructure.rabbitmq.dto.ProductDTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDTOToDomainUnitTest {

    @InjectMocks
    private OrderDTOToDomain orderDTOToDomain;

    @Test
    @DisplayName("Should return null when input OrderDTO is null")
    void execute_nullOrderDTOTest() {
        Order result = orderDTOToDomain.execute(null);
        assertNull(result);
    }

    @Test
    @DisplayName("Should convert OrderDTO to Order domain object correctly")
    void execute_ShouldConvertOrderDTOToOrder() {
        OrderDTO orderDTO = Instancio.create(OrderDTO.class);
        List<OrderItem> expectedOrderItems = Instancio.ofList(OrderItem.class)
                .size(orderDTO.getProductList().size())
                .create();

        Instant fixedInstant = Instant.now();
        ZonedDateTime fixedZonedDateTime = ZonedDateTime.ofInstant(fixedInstant, ZoneId.systemDefault());

        try (MockedStatic<OrderItemDTOToDomain> mockedOrderItemConverter = mockStatic(OrderItemDTOToDomain.class);
             MockedStatic<ZonedDateTime> mockedZonedDateTime = mockStatic(ZonedDateTime.class)) {

            mockedZonedDateTime.when(ZonedDateTime::now).thenReturn(fixedZonedDateTime);

            for (int i = 0; i < orderDTO.getProductList().size(); i++) {
                ProductDTO productDTO = orderDTO.getProductList().get(i);
                OrderItem orderItem = expectedOrderItems.get(i);
                when(OrderItemDTOToDomain.execute(productDTO)).thenReturn(orderItem);
            }

            Order order = orderDTOToDomain.execute(orderDTO);

            assertThat(order).isNotNull();
            assertThat(order.getOrderItems()).isEqualTo(expectedOrderItems);
            assertThat(order.getCreditCardNumber()).isEqualTo(orderDTO.getPaymentMethod().getCardNumber());
            assertThat(order.getStatus()).isEqualTo(StatusEnum.OPEN);
            assertThat(order.getDateCreated()).isEqualTo(fixedZonedDateTime);
        }
    }

    @Test
    @DisplayName("Should convert OrderDTO with empty product list to Order with empty order items")
    void execute_ShouldHandleEmptyProductList() {
        OrderDTO orderDTO = Instancio.of(OrderDTO.class)
                .set(org.instancio.Select.field("productList"), Collections.emptyList())
                .create();

        Instant fixedInstant = Instant.now();
        ZonedDateTime fixedZonedDateTime = ZonedDateTime.ofInstant(fixedInstant, ZoneId.systemDefault());

        try (MockedStatic<OrderItemDTOToDomain> mockedOrderItemConverter = mockStatic(OrderItemDTOToDomain.class);
             MockedStatic<ZonedDateTime> mockedZonedDateTime = mockStatic(ZonedDateTime.class)) {

            mockedZonedDateTime.when(ZonedDateTime::now).thenReturn(fixedZonedDateTime);

            Order order = orderDTOToDomain.execute(orderDTO);

            assertThat(order).isNotNull();
            assertThat(order.getOrderItems()).isNotNull().isEmpty();
            assertThat(order.getStatus()).isEqualTo(StatusEnum.OPEN);
            assertThat(order.getDateCreated()).isEqualTo(fixedZonedDateTime);
        }
    }
}