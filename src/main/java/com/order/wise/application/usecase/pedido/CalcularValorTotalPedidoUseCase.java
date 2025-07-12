package com.order.wise.application.usecase.pedido;

import com.order.wise.domain.ItensPedidos;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CalcularValorTotalPedidoUseCase {


   //Função para calcular o valor total do pedido de acordo com a soma de todos os subtotais de cada item
    public BigDecimal calcularValorTotal(List<ItensPedidos> itensPedidos){
        return itensPedidos.stream()
                .map(ItensPedidos::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
