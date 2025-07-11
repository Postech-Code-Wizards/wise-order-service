package com.order.wise.usecase.pedido;

import com.order.wise.converter.PedidoConverter;
import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;
import com.order.wise.usecase.itenspedido.CalcularSubtotalUseCase;
import com.order.wise.usecase.itenspedido.VerificaDisponibilidadeItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessarPedidoUseCase{

    private final CreatePedidoUseCase createPedidoUseCase;
    private final CalcularSubtotalUseCase calcularSubtotalUseCase;
    private final CalcularValorTotalPedidoUseCase calcularValorTotalPedidoUseCase;
    private final VerificaDisponibilidadeItemUseCase verificaDisponibilidadeItemUseCase = null;
    private final PedidoConverter pedidoConverter;
    private final UpdateStatusPedidoUseCase updateStatusPedidoUseCase;
    private final PagamentoUseCase pagamentoUseCase;
    private final FinalizarPedidoUseCase finalizarPedidoUseCase;

    //Pedido consumerReceiver = pedido;

    public Status processarPedido(Pedido pedido){

        pedido = createPedidoUseCase.createPedido(pedido);
        Status statusMomento = verificaDisponibilidadeItemUseCase.verificarBaixarEstoque(pedido);
        if (statusMomento == Status.FECHADO_SEM_ESTOQUE) {
            return statusMomento;
        } else {
            Status statusFinal = pagamentoUseCase.processar(pedido);
            return statusFinal;
        }

    }

}
