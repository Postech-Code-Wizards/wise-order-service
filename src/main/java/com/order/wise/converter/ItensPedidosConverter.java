package com.order.wise.converter;

import com.order.wise.domain.ItensPedidos;
import com.order.wise.domain.Pedido;
import com.order.wise.gateway.database.entities.ItensPedidosEntity;
import com.order.wise.gateway.database.entities.PedidoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ItensPedidosConverter {

    public List<ItensPedidosEntity> toEntity(List<ItensPedidos> itensPedidos, PedidoEntity pedidoEntity){

        List<ItensPedidosEntity> itensPedidosEntities = itensPedidos
                .stream()
                .map(itens -> {
                    ItensPedidosEntity itensPedidosEntity = new ItensPedidosEntity();
                    itensPedidosEntity.setPedido_id(pedidoEntity);
                    itensPedidosEntity.setProdutoId(itens .getProdutoId());
                    itensPedidosEntity.setNomeProduto(itens .getNomeProduto());
                    itensPedidosEntity.setQuantidade(itens .getQuantidade());
                    itensPedidosEntity.setPrecoUnitario(itens .getPrecoUnitario());
                    itensPedidosEntity.setSubtotal(itens .getSubtotal());
                    return itensPedidosEntity;
                }).toList();
        return itensPedidosEntities;
    }

    public List<ItensPedidos> toDomain(List<ItensPedidosEntity> itensPedidosEntities, Pedido pedido){

        List<ItensPedidos> itensPedidos = itensPedidosEntities
                .stream()
                .map(p -> new ItensPedidos(
                        p.getId(),
                        pedido,
                        p.getProdutoId(),
                        p.getNomeProduto(),
                        p.getQuantidade(),
                        p.getPrecoUnitario(),
                        p.getSubtotal()
                )).toList();

        return itensPedidos;

    }

}
