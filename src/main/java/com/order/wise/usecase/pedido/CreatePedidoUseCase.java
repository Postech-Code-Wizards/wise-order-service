package com.order.wise.usecase.pedido;


import com.order.wise.domain.Pedido;
import com.order.wise.gateway.PedidoGateway;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePedidoUseCase {
    private final PedidoGateway pedidoGateway;


    //Função responsavel pela criação do pedido na tabela
    public void createPedido(Pedido pedido) {

        var pedidoSaved = pedidoGateway.save(pedido);
    }


}
