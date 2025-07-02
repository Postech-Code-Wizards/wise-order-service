package com.order.wise.gateway;

import com.order.wise.domain.ItensPedidos;
import com.order.wise.gateway.database.entities.PedidoEntity;

import java.util.List;

public interface ItensPedidosGateway {

    //void save(ItensPedidos itensPedidos);

    void save(List<ItensPedidos> itensPedidos, PedidoEntity pedidoEntity);
}
