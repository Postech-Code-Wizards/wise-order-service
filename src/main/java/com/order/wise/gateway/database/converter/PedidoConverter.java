package com.order.wise.gateway.database.converter;

import com.order.wise.domain.Pedido;
import com.order.wise.gateway.database.entities.PedidoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoConverter {

    private final ItensPedidosConverter itensPedidosConverter;

    public PedidoEntity toEntity(Pedido pedido) {

        if (pedido == null) return null;

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(pedido.getId());
        pedidoEntity.setClienteId(pedido.getClienteId());
        pedidoEntity.setStatus(pedido.getStatus());
        pedidoEntity.setDataCriacao(pedido.getDataCriacao());
        pedidoEntity.setCartaoCredito(pedido.getCartaoCredito());
        pedidoEntity.setPagamentoId(pedido.getPagamentoId());
        pedidoEntity.setValorTotal(pedido.getValorTotal());
        pedidoEntity.setItensPedidos(itensPedidosConverter.toEntity(pedido.getItensPedidos(), pedidoEntity));

        return pedidoEntity;

    }

    public Pedido toDomain(PedidoEntity pedidoEntity) {
        if (pedidoEntity == null) return null;

        return Pedido.builder()
                .id(pedidoEntity.getId())
                .clienteId(pedidoEntity.getClienteId())
                .status(pedidoEntity.getStatus())
                .dataCriacao(pedidoEntity.getDataCriacao())
                .cartaoCredito(pedidoEntity.getCartaoCredito())
                .pagamentoId(pedidoEntity.getPagamentoId())
                .valorTotal(pedidoEntity.getValorTotal())
                .itensPedidos(itensPedidosConverter.toDomain(pedidoEntity.getItensPedidos(), null))
                .build();

    }

}
