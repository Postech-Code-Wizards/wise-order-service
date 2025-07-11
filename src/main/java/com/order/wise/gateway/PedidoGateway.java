package com.order.wise.gateway;

import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;

import java.util.List;

public interface PedidoGateway {

    Pedido save(Pedido pedido);

    Pedido getById(Long id);

    List<Pedido> getAllPedidos();

   // Pedido updateStatus(Long id, String status);

    Pedido updateFinalizarPedido(Pedido pedido);


    void updateStatus(Long id, Status status);
}
