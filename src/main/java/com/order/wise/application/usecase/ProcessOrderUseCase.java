package com.order.wise.application.usecase;

import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;
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

    public Status execute(Pedido pedido) {

        log.info("Processing order: {}", pedido);

        pedido = createOrderUseCase.createOrder(pedido);
        Status statusMomento = lowStockUseCase.execute(pedido);
        if (statusMomento == Status.FECHADO_SEM_ESTOQUE) {
            return statusMomento;
        } else {
            return paymentUseCase.execute(pedido);
        }
    }
}
