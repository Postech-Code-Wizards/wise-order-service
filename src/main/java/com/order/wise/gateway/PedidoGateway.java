package com.order.wise.gateway;

import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;

public interface PedidoGateway {

    Pedido save(Pedido pedido);

    Pedido getById(Long id);

    void updateStatus(Long id, Status status);
}
