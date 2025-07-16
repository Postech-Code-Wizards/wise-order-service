package com.order.wise.application.usecase;

import com.order.wise.domain.Pedido;
import com.order.wise.gateway.PedidoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateOrderUseCase {

    private final PedidoGateway pedidoGateway;

    public Pedido createOrder(Pedido pedido) {
        log.info("Creating order: {}", pedido);
        return pedidoGateway.save(pedido);
    }
}
