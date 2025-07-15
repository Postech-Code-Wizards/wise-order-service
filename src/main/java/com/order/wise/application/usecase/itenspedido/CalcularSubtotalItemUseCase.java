package com.order.wise.application.usecase.itenspedido;

import com.order.wise.domain.ItensPedidos;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CalcularSubtotalItemUseCase {

    //Função para calcular o subtotal de cada item de acordo com a quantidade e valor unitário
    public List<ItensPedidos> calcularSubtotalItens(List<ItensPedidos> itensPedidos){

        for (ItensPedidos itensPedido : itensPedidos) {
            BigDecimal subtotal = itensPedido.getPrecoUnitario().multiply(
                    BigDecimal.valueOf(itensPedido.getQuantidade())
                            .setScale(2, RoundingMode.HALF_UP));
            itensPedido.setSubtotal(subtotal);
        }
        return itensPedidos;
    }
}
