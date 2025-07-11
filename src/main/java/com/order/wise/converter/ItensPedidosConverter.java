package com.order.wise.converter;

import com.order.wise.domain.ItensPedidos;
import com.order.wise.domain.Pedido;
import com.order.wise.domain.dtos.request.BaixaEstoqueRequest;
import com.order.wise.gateway.database.entities.ItensPedidosEntity;
import com.order.wise.gateway.database.entities.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
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

    public List<BaixaEstoqueRequest> toBaixaEstoqueRequest(List<ItensPedidos> itensPedidos){

        List<BaixaEstoqueRequest> pedidoBaixaEstoque = itensPedidos.stream()
                .map(item -> new BaixaEstoqueRequest(
                        item.getProdutoId(),
                        item.getQuantidade(),
                        item.getPedido_id().getId()
                ))
                .toList();
        return pedidoBaixaEstoque;

    }

}
