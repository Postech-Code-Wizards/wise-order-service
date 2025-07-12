package com.order.wise.application.usecase;

import com.order.wise.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@RequiredArgsConstructor
@Service
public class CreateOrderUseCase {

    public void createOrder(Pedido pedido) {

        log.info("Creating order: {}", pedido);
        // todo - Aqui você pode implementar a lógica para criar um pedido
        // todo - Fazer chamada ao gateway ou repositório para salvar o pedido
        // todo - Chamar outros metodos nenessários para completar o processo de criação do pedido
        // todo - Tratar mudança de status se necessário

    }
}
