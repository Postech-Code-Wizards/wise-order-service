package com.order.wise.gateway.database.converter;

import com.order.wise.domain.Pedido;
import com.order.wise.gateway.database.entities.PedidoEntity;
import com.order.wise.gateway.messaging.rabbitMQ.dto.PaymentDTO;
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

        return pedido;
    }

    public PaymentDTO toPaymentDTO(Pedido pedido){

        return new PaymentDTO(
                pedido.getValorTotal(),
                pedido.getCartaoCredito(),
                pedido.getId(),
                pedido.getClienteId()
        );
    }
}
