package com.order.wise.application.usecase;

import com.order.wise.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessOrderUseCase {

    private final CreateOrderUseCase createOrderUseCase;
    private final LowStockUseCase lowStockUseCase;
    private final PaymentUseCase paymentUseCase;

    public void execute(Pedido pedido) {

        log.info("Processing order: {}", pedido);
        Pedido pedidoSalvo = createOrderUseCase.createOrder(pedido);
        lowStockUseCase.execute(pedidoSalvo);
        paymentUseCase.execute(pedidoSalvo);
    }
}
