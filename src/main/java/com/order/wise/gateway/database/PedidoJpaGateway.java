package com.order.wise.gateway.database;

import com.order.wise.domain.Pedido;
import com.order.wise.domain.enums.Status;
import com.order.wise.gateway.PedidoGateway;
import com.order.wise.gateway.database.converter.PedidoConverter;
import com.order.wise.gateway.database.entities.PedidoEntity;
import com.order.wise.gateway.database.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoJpaGateway implements PedidoGateway {
    private final PedidoRepository pedidoRepository;
    private final PedidoConverter pedidoConverter;


    @Override
    public Pedido save(Pedido pedido) {

        PedidoEntity pedidoEntity = pedidoConverter.toEntity(pedido);
        return pedidoConverter.toDomain(pedidoRepository.save(pedidoEntity));

    }

    @Override
    public Pedido getById(Long id) {
        return pedidoConverter.toDomain(pedidoRepository.findById(id).orElse(null));
    }

    @Override
    public void updateStatus(Long id, Status status, Long idPagamento) {

        PedidoEntity pedidoEntity = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));

        pedidoEntity.setStatus(status);
        pedidoEntity.setPagamentoId(idPagamento);
        pedidoRepository.save(pedidoEntity);

    }
}
