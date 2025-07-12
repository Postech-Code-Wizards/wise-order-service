package com.order.wise.application.usecase.pedido;

import com.order.wise.gateway.database.converter.PedidoConverter;
import com.order.wise.domain.Pedido;
import com.order.wise.application.facade.dtos.request.PagamentoRequest;
import com.order.wise.domain.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoUseCase {

    private final PedidoConverter pedidoConverter;
    private final UpdateStatusPedidoUseCase updateStatusPedidoUseCase;
    private final FinalizarPedidoUseCase finalizarPedidoUseCase;



    //Função que irá acionar o pagamento e obter retorno de sucesso ou falha
    public Status processar(Pedido pedido){
        return null;
//        PagamentoRequest pagamentoRequest = pedidoConverter.toPagamentoRequest(pedido);
//
//        Long producerProcessarPagamento = producer.processar(pagamentoRequest);
//
//        PagamentoResponse pagamentoResponse = new PagamentoResponse();
//
//        if(producerProcessarPagamento == null ){
//            updateStatusPedidoUseCase.updateStatusPedido(pedido.getId(), Status.FECHADO_SEM_CREDITO);
//            pedido.setStatus(Status.FECHADO_SEM_CREDITO);
//            pagamentoResponse.setId(null);
//            return Status.FECHADO_SEM_CREDITO;
//
//        }else{
//                pedido.setStatus(Status.FECHADO_COM_SUCESSO);
//                pedido.setPagamentoId(pagamentoResponse.getId());
//                finalizarPedidoUseCase.salvarPedido(pedido);
//
//                return Status.FECHADO_COM_SUCESSO;
//
//        }

    }
}
