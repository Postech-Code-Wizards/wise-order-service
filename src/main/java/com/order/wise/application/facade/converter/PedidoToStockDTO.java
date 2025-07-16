package com.order.wise.application.facade.converter;

import com.order.wise.domain.Pedido;
import com.order.wise.gateway.messaging.rabbitmq.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoToStockDTO {

    public List<StockDTO> execute(Pedido pedido) {

        return pedido.getItensPedidos().stream()
                .map(item -> new StockDTO(
                        item.getProdutoId(),
                        item.getQuantidade(),
                        pedido.getId()
                ))
                .toList();
    }

}
