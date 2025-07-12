package com.order.wise.application.usecase;

import com.order.wise.domain.Pedido;
import com.order.wise.infrastructure.messaging.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessOrderUseCase {

    public void execute(Pedido pedido) {

        log.info("Processing order: {}", pedido);

        // todo - Aqui você pode implementar a lógica para processar o pedido recebido
        // todo - Chamar o metodo CreateOrder.execute para salvar o pedido na base
        // todo - Chamar o metodo CreateOrderItem.execute para salvar os itens do pedido na base (caso não seja feito na mesma transação)
        // todo - Chamar o metodo LowStockUseCase.execute para baixar o estoque dos produtos do pedido
        // todo - Chamar o metodo PaymentUseCase.execute para processar o pagamento do pedido
    }
}
