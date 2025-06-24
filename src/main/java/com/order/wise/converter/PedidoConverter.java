package com.order.wise.converter;

import com.order.wise.domain.ItensPedidos;
import com.order.wise.domain.Pedido;
import com.order.wise.gateway.database.entities.ItensPedidosEntity;
import com.order.wise.gateway.database.entities.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoConverter {

    public PedidoEntity toEntity(Pedido pedido) {

        if( pedido == null)return null;

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(pedido.getId());
        pedidoEntity.setClienteId(pedido.getClienteId());
        pedidoEntity.setStatus(pedido.getStatus());
        pedidoEntity.setDataCriacao(pedido.getDataCriacao());
        pedidoEntity.setValorTotal(pedido.getValorTotal());

        List<ItensPedidosEntity> itensPedidosEntities = pedido.getItensPedidos()
                .stream()
                .map(pe -> {
                    ItensPedidosEntity itensPedidosEntity = new ItensPedidosEntity();
                    itensPedidosEntity.setPedido_id(pedidoEntity);
                    itensPedidosEntity.setProdutoId(pe.getProdutoId());
                    itensPedidosEntity.setNomeProduto(pe.getNomeProduto());
                    itensPedidosEntity.setQuantidade(pe.getQuantidade());
                    itensPedidosEntity.setPrecoUnitario(pe.getPrecoUnitario());
                    itensPedidosEntity.setSubtotal(pe.getSubtotal());
                    return itensPedidosEntity;
                }).toList();

        pedidoEntity.setItensPedidos(itensPedidosEntities);
        return pedidoEntity;

    }

    public Pedido toDomain(PedidoEntity pedidoEntity) {
        if( pedidoEntity == null)return null;

        Pedido pedido = new Pedido();
        pedido.setId(pedidoEntity.getId());
        pedido.setClienteId(pedidoEntity.getClienteId());
        pedido.setStatus(pedidoEntity.getStatus());
        pedido.setDataCriacao(pedidoEntity.getDataCriacao());
        pedido.setValorTotal(pedidoEntity.getValorTotal());

        List<ItensPedidos> itensPedidos = pedidoEntity.getItensPedidos()
                .stream()
                .map(p -> new ItensPedidos(
                        p.getId(),
                        pedido,
                        p.getProdutoId(),
                        p.getNomeProduto(),
                        p.getQuantidade(),
                        p.getPrecoUnitario(),
                        p.getSubtotal()
                )).collect(Collectors.toList());

        pedido.setItensPedidos(itensPedidos);
        return pedido;
    }


}
