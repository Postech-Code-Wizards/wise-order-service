package com.order.wise.application.usecase;

import com.order.wise.application.facade.converter.PedidoToStockDTO;
import com.order.wise.domain.Pedido;
import com.order.wise.gateway.LowStockGateway;
import com.order.wise.gateway.messaging.rabbitmq.dto.StockDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LowStockUseCase {

    private final PedidoToStockDTO pedidoToStockDTO;
    private final LowStockGateway lowStockGateway;

    public void execute(Pedido pedido) {
        log.info("Baixando estoque para o pedido: {}", pedido.getId());
        List<StockDTO> stockRequests = pedidoToStockDTO.execute(pedido);
        lowStockGateway.send(stockRequests);
    }

}
