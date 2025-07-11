package com.order.wise.gateway.database;

import com.order.wise.converter.PedidoConverter;
import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;
import com.order.wise.gateway.PedidoGateway;
import com.order.wise.gateway.database.entities.PedidoEntity;
import com.order.wise.gateway.database.repositories.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PedidoJpaGateway implements PedidoGateway {
    private final PedidoRepository pedidoRepository;
    private final PedidoConverter pedidoConverter;


    @Override
    public Pedido save(Pedido pedido) {

        PedidoEntity pedidoEntity =  pedidoConverter.toEntity(pedido);
        return pedidoConverter.toDomain(pedidoRepository.save(pedidoEntity));

    }

    @Override
    public Pedido getById(Long id) {
        return pedidoConverter.toDomain(pedidoRepository.findById(id).orElse(null));
    }

    @Override
    public List<Pedido> getAllPedidos(){

        List<PedidoEntity> pedidosEntity = pedidoRepository.findPedidos();
        if(pedidosEntity == null)return null;

        List<Pedido> pedido = pedidosEntity.stream()
                                           .map(pedidoConverter::toDomain)
                                           .collect(Collectors.toList());

        return pedido;
    }

    @Override
    public Pedido updateFinalizarPedido(Pedido pedido){

        PedidoEntity pedidoEntity = pedidoRepository.findById(pedido.getId()).orElse(null);
        pedidoEntity.setStatus(pedido.getStatus());
        pedidoEntity.setPagamentoId(pedido.getPagamentoId());

        pedidoRepository.save(pedidoEntity);
        return pedidoConverter.toDomain(pedidoEntity);
    }

    @Override
    public void updateStatus(Long id, Status status) {

        PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElse(null);

        pedidoEntity.setStatus(status);
        pedidoRepository.save(pedidoEntity);

    }
}
