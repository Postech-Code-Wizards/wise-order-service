package com.order.wise.converter;

import com.order.wise.domain.Pedido;
import com.order.wise.gateway.database.entities.PedidoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PedidoConverter {

    private final ItensPedidosConverter itensPedidosConverter;

    public PedidoEntity toEntity(Pedido pedido) {

        if( pedido == null)return null;

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(pedido.getId());
        pedidoEntity.setClienteId(pedido.getClienteId());
        pedidoEntity.setStatus(pedido.getStatus());
        pedidoEntity.setDataCriacao(pedido.getDataCriacao());
        pedidoEntity.setCartaoCredito(pedido.getCartaoCredito());
        pedidoEntity.setPagamentoId(pedido.getPagamentoId());
        pedidoEntity.setValorTotal(pedido.getValorTotal());
        pedidoEntity.setItensPedidos(itensPedidosConverter.toEntity(pedido.getItensPedidos(), pedidoEntity));

//        List<ItensPedidosEntity> itensPedidosEntities = pedido.getItensPedidos()
//                .stream()
//                .map(pe -> {
//                    ItensPedidosEntity itensPedidosEntity = new ItensPedidosEntity();
//                    itensPedidosEntity.setPedido_id(pedidoEntity);
//                    itensPedidosEntity.setProdutoId(pe.getProdutoId());
//                    itensPedidosEntity.setNomeProduto(pe.getNomeProduto());
//                    itensPedidosEntity.setQuantidade(pe.getQuantidade());
//                    itensPedidosEntity.setPrecoUnitario(pe.getPrecoUnitario());
//                    itensPedidosEntity.setSubtotal(pe.getSubtotal());
//                    return itensPedidosEntity;
//                  }).toList();
//
//        pedidoEntity.setItensPedidos(itensPedidosEntities);
        return pedidoEntity;

    }

    public Pedido toDomain(PedidoEntity pedidoEntity) {
        if( pedidoEntity == null)return null;

        Pedido pedido = new Pedido();
        pedido.setId(pedidoEntity.getId());
        pedido.setClienteId(pedidoEntity.getClienteId());
        pedido.setStatus(pedidoEntity.getStatus());
        pedido.setDataCriacao(pedidoEntity.getDataCriacao());
        pedido.setCartaoCredito(pedidoEntity.getCartaoCredito());
        pedido.setPagamentoId(pedidoEntity.getPagamentoId());
        pedido.setValorTotal(pedidoEntity.getValorTotal());
        pedido.setItensPedidos(itensPedidosConverter.toDomain(pedidoEntity.getItensPedidos(), pedido));

//        List<ItensPedidos> itensPedidos = pedidoEntity.getItensPedidos()
//                .stream()
//                .map(p -> new ItensPedidos(
//                        p.getId(),
//                        pedido,
//                        p.getProdutoId(),
//                        p.getNomeProduto(),
//                        p.getQuantidade(),
//                        p.getPrecoUnitario(),
//                        p.getSubtotal()
//                )).collect(Collectors.toList());
//
//        pedido.setItensPedidos(itensPedidos);
        return pedido;
    }


}
