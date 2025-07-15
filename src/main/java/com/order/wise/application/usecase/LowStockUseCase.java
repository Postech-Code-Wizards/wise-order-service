package com.order.wise.application.usecase;

import com.order.wise.application.usecase.pedido.UpdateStatusPedidoUseCase;
import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;
import com.order.wise.gateway.LowStockGateway;
import com.order.wise.gateway.database.converter.ItensPedidosConverter;
import com.order.wise.gateway.messaging.rabbitMQ.dto.StockDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LowStockUseCase {

    private final ItensPedidosConverter itensPedidosConverter;
    private final LowStockGateway lowStockGateway;
    private final UpdateStatusPedidoUseCase updateStatusPedidoUseCase;

    public Status execute(Pedido pedido) {

        List<StockDTO> stockRequests = itensPedidosConverter.toStockDto(pedido.getItensPedidos());

        log.info("Baixando estoque para o pedido: {}", pedido.getId());
        lowStockGateway.send(stockRequests);

        //todo - Resposta do Stock de acordo com o Id do pedido, seráusada para tratar o status
        //Boolean responseStock = lowStockGateway.findById(pedido.getId());

        if (Boolean.FALSE.equals(responseStock)) {
            updateStatusPedidoUseCase.updateStatusPedido(pedido.getId(), Status.FECHADO_SEM_ESTOQUE, pedido.getPagamentoId());
            return Status.FECHADO_SEM_ESTOQUE;

        } else {
            return Status.ABERTO;
        }

    }

}
