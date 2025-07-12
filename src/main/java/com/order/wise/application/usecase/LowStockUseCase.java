package com.order.wise.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LowStockUseCase {

    public void execute(String produtoId, int quantidade) {

        log.info("Baixando estoque do produto: {} com quantidade: {}", produtoId, quantidade);

        // todo - Aqui você pode implementar a lógica para baixar o estoque do produto
        // todo - Chamar o metodo LowStockGateway.execute para enviar a notificação de baixa de estoque
        // todo - Tratar mudança de status se necessário

    }

}
