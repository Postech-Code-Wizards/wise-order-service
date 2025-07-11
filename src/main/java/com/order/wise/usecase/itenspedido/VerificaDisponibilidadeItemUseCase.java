package com.order.wise.usecase.itenspedido;

import com.order.wise.converter.ItensPedidosConverter;
import com.order.wise.domain.ItensPedidos;
import com.order.wise.domain.Pedido;
import com.order.wise.domain.dtos.request.BaixaEstoqueRequest;
import com.order.wise.domain.enums.Status;
import com.order.wise.usecase.pedido.UpdateStatusPedidoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class VerificaDisponibilidadeItemUseCase {

    private final UpdateStatusPedidoUseCase updateStatusPedidoUseCase;
    private final ItensPedidosConverter itensPedidosConverter;

    //solicita a baixa no estoque utilizando um dto na requisição
    public Status verificarBaixarEstoque(Pedido pedido ) {

        List<ItensPedidos> itensPedidos = pedido.getItensPedidos();

        List<BaixaEstoqueRequest> pedidoBaixaEstoque = itensPedidosConverter.toBaixaEstoqueRequest(itensPedidos);

        int respostaEstoque = estoqueProducer.enviarBaixaEstoque(pedidoBaixaEstoque);

        if (respostaEstoque == 0) {
            updateStatusPedidoUseCase.updateStatusPedido(pedido.getId(), Status.FECHADO_SEM_ESTOQUE);
            return Status.FECHADO_SEM_ESTOQUE;

        } else {
            return pedido.getStatus();
        }
    }

}
