package com.order.wise.gateway.database;

import com.order.wise.gateway.database.converter.ItensPedidosConverter;
import com.order.wise.domain.ItensPedidos;
import com.order.wise.gateway.ItensPedidosGateway;
import com.order.wise.gateway.database.entities.ItensPedidosEntity;
import com.order.wise.gateway.database.entities.PedidoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ItensPedidosJpaGateway implements ItensPedidosGateway {

    private final ItensPedidosConverter itensPedidosConverter;

//    @Override
//    public void save(List<ItensPedidos> itensPedidos, PedidoEntity pedidoEntity) {
//
//        ItensPedidosEntity itensPedidosEntity = (ItensPedidosEntity) itensPedidosConverter.toEntity(itensPedidos, pedidoEntity);
//
//    }

}
