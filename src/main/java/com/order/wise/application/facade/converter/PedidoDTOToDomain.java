package com.order.wise.application.facade.converter;

import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;
import com.order.wise.infrastructure.messaging.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class PedidoDTOToDomain {

    public Pedido execute(OrderDTO orderDTO) {
        return Pedido.builder()
                .valorTotal(orderDTO.getTotalPrice())
                .clienteId(orderDTO.getClient().getId().longValue())
                .itensPedidos(orderDTO.getProductList().stream()
                        .map(ItemPedidoDTOToDomain::execute)
                        .toList())
                .cartaoCredito(orderDTO.getPaymentMethod().getCardNumber())
                .status(Status.ABERTO)
                .dataCriacao(ZonedDateTime.now())
                .build();
    }
}
