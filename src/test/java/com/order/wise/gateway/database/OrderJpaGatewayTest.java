package com.order.wise.gateway.database;

import com.order.wise.domain.Order;
import com.order.wise.domain.enums.StatusEnum;
import com.order.wise.gateway.database.converter.OrderConverter;
import com.order.wise.gateway.database.entities.OrderEntity;
import com.order.wise.gateway.database.repositories.OrderRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderJpaGatewayUnitTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderConverter orderConverter;

    @InjectMocks
    private OrderJpaGateway orderJpaGateway;

    @Test
    @DisplayName("Should save an order by converting to entity, saving, and converting back to domain")
    void save_ShouldSaveOrderSuccessfully() {

        Order domainOrder = Instancio.create(Order.class);
        OrderEntity orderEntityToSave = Instancio.create(OrderEntity.class);
        OrderEntity savedOrderEntity = Instancio.create(OrderEntity.class);
        Order savedDomainOrder = Instancio.create(Order.class);

        when(orderConverter.toEntity(domainOrder)).thenReturn(orderEntityToSave);
        when(orderRepository.save(orderEntityToSave)).thenReturn(savedOrderEntity);
        when(orderConverter.toDomain(savedOrderEntity)).thenReturn(savedDomainOrder);

        Order result = orderJpaGateway.save(domainOrder);

        assertThat(result).isEqualTo(savedDomainOrder);
        verify(orderConverter).toEntity(domainOrder);
        verify(orderRepository).save(orderEntityToSave);
        verify(orderConverter).toDomain(savedOrderEntity);
    }

    @Test
    @DisplayName("Should update the status of an existing order")
    void updateStatus_ShouldUpdateOrderStatus() {

        Long orderId = Instancio.create(Long.class);
        StatusEnum newStatus = Instancio.create(StatusEnum.class);
        OrderEntity existingOrderEntity = Instancio.create(OrderEntity.class);
        existingOrderEntity.setId(orderId); // Ensure ID matches

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrderEntity));

        orderJpaGateway.updateStatus(orderId, newStatus);

        assertThat(existingOrderEntity.getStatus()).isEqualTo(newStatus);
        verify(orderRepository).findById(orderId);
        verify(orderRepository).save(existingOrderEntity);
    }

    @Test
    @DisplayName("Should throw RuntimeException when updating status for a non-existent order")
    void updateStatus_ShouldThrowExceptionWhenOrderNotFound() {

        Long orderId = Instancio.create(Long.class);
        StatusEnum newStatus = Instancio.create(StatusEnum.class);

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderJpaGateway.updateStatus(orderId, newStatus))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Order not found with ID: " + orderId);
        verify(orderRepository).findById(orderId);
        verify(orderRepository).findById(orderId);
    }

    @Test
    @DisplayName("Should update the payment ID of an existing order")
    void updatePayment_ShouldUpdateOrderPaymentId() {

        Long orderId = Instancio.create(Long.class);
        String newPaymentId = Instancio.create(String.class);
        OrderEntity existingOrderEntity = Instancio.create(OrderEntity.class);
        existingOrderEntity.setId(orderId); // Ensure ID matches

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrderEntity));

        orderJpaGateway.updatePayment(orderId, newPaymentId);

        assertThat(existingOrderEntity.getPaymentId()).isEqualTo(newPaymentId);
        verify(orderRepository).findById(orderId);
        verify(orderRepository).save(existingOrderEntity);
    }

    @Test
    @DisplayName("Should throw RuntimeException when updating payment for a non-existent order")
    void updatePayment_ShouldThrowExceptionWhenOrderNotFound() {

        Long orderId = Instancio.create(Long.class);
        String newPaymentId = Instancio.create(String.class);

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderJpaGateway.updatePayment(orderId, newPaymentId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Order not found with ID: " + orderId);
        verify(orderRepository).findById(orderId);
    }

    @Test
    @DisplayName("Should find an order by ID and convert it to domain object")
    void findById_ShouldReturnOrderWhenFound() {

        Long orderId = Instancio.create(Long.class);
        OrderEntity foundOrderEntity = Instancio.create(OrderEntity.class);
        foundOrderEntity.setId(orderId); // Ensure ID matches
        Order expectedDomainOrder = Instancio.create(Order.class);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(foundOrderEntity));
        when(orderConverter.toDomain(foundOrderEntity)).thenReturn(expectedDomainOrder);

        Order result = orderJpaGateway.findById(orderId);

        assertThat(result).isEqualTo(expectedDomainOrder);
        verify(orderRepository).findById(orderId);
        verify(orderConverter).toDomain(foundOrderEntity);
    }

    @Test
    @DisplayName("Should throw RuntimeException when finding a non-existent order by ID")
    void findById_ShouldThrowExceptionWhenOrderNotFound() {

        Long orderId = Instancio.create(Long.class);

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderJpaGateway.findById(orderId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Order not found with ID: " + orderId);
        verify(orderRepository).findById(orderId);
    }
}