package com.order.wise.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentUseCase {

    public void execute(String orderId, double amount) {
        log.info("Processing payment for order: {} with amount: {}", orderId, amount);

        // todo - Aqui você pode implementar a lógica para processar o pagamento do pedido
        // todo - Chamar o metodo PaymentGateway.execute para enviar a notificação de pagamento
        // todo - Tratar mudança de status se necessário

    }
}
