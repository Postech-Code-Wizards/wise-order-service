package com.order.wise.application.usecase;

import com.order.wise.application.usecase.pedido.UpdateStatusPedidoUseCase;
import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;
import com.order.wise.gateway.PaymentGateway;
import com.order.wise.gateway.database.converter.ItensPedidosConverter;
import com.order.wise.gateway.database.converter.PedidoConverter;
import com.order.wise.gateway.messaging.rabbitMQ.dto.PaymentDTO;
import com.order.wise.gateway.messaging.rabbitMQ.dto.StockDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentUseCase {

    private final PedidoConverter pedidoConverter;
    private final PaymentGateway paymentGateway;
    private final UpdateStatusPedidoUseCase updateStatusPedidoUseCase;
    private final ItensPedidosConverter itensPedidosConverter;

    public Status execute(Pedido pedido) {

        log.info("Processing payment for order: {} with amount: {}", pedido.getId(), pedido.getValorTotal());

        PaymentDTO paymentRequest = pedidoConverter.toPaymentDTO(pedido);

        paymentGateway.send(paymentRequest);

        //todo - Pagamento deve retornar ID para ser tratado posteriormente
        //Long responsePayment = paymentGateway.findById(pedido.getId());

        //todo - Aqui será tratado o status final do pedido de acordo com o responsePayment
        if (responsePayment == null) {
            updateStatusPedidoUseCase.updateStatusPedido(pedido.getId(), Status.FECHADO_SEM_CREDITO, responsePayment);
            List<StockDTO> stockRequests = itensPedidosConverter.toStockDto(pedido.getItensPedidos());
            log.info("Resetando estoque para o pedido: {}", pedido.getId());

            //todo - Aqui será acionada a fila de reposição de itens no stock
            //ResetStock.send(stockRequests);

            return Status.FECHADO_SEM_CREDITO;

        } else {
            updateStatusPedidoUseCase.updateStatusPedido(pedido.getId(), Status.FECHADO_COM_SUCESSO, responsePayment);
            return Status.FECHADO_COM_SUCESSO;
        }
    }
}
