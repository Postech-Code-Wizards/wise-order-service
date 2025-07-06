package com.order.wise.usecase.pedido;

import com.order.wise.domain.ItensPedidos;
import com.order.wise.domain.Pedido;
import com.order.wise.domain.dtos.request.BaixaEstoqueRequest;
import com.order.wise.domain.dtos.request.PagamentoRequest;
import com.order.wise.domain.dtos.response.PagamentoResponse;
import com.order.wise.domain.dtos.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessarPedidoUseCase {

    private final CreatePedidoUseCase createPedidoUseCase;


    //Antes de chamar essa função a lista de itens terá apenas os IDs e quantidade de cada item
    //após a resposta a lista estará quase completa faltando apenas o subtotal.
    public List<ItensPedidos> obterItensPedidosById(List<ItensPedidos> ids){

        List<Long> idsConsumer= new ArrayList<>();
        List<ItensPedidos> itensPedido = new ArrayList<>();
        for (ItensPedidos id : ids){
            idsConsumer.add(id.getProdutoId());
        }

        //Criar logica para obter informações sobre os itens atraves da fila de produtos mandando a lista de IDs na entrada


        return itensPedido;

    }

    //Popular DTO com os dados do usuario
    public UserResponse obterDadosClienteById(Long id){

        UserResponse usuario = new UserResponse();

        //Logica para obter dados do usuario

        return usuario;

    }

    //solicita a baixa no estoque utilizando um dto na requisição
    public void verificarBaixarEstoque(List<ItensPedidos> itensPedidos){

        List<BaixaEstoqueRequest> pedidosBaixaEstoque = itensPedidos.stream()
                .map(item -> new BaixaEstoqueRequest(
                        item.getProdutoId(),
                        item.getQuantidade(),
                        item.getPedido_id().getId()
                ))
                .toList();

        //estoqueProducer.enviarBaixaEstoque(pedidosBaixaEstoque);

        //Tratar status do pedido caso o estoque retorne que não tem o item

    }


    //Função para calcular o subtotal de cada item de acordo com a quantidade e valor unitário
    public List<ItensPedidos> calcladoraSubtotal(List<ItensPedidos> itensPedidos){

        for (ItensPedidos itensPedido : itensPedidos) {
            BigDecimal subtotal = itensPedido.getPrecoUnitario().multiply(
                    BigDecimal.valueOf(itensPedido.getQuantidade())
            );
            itensPedido.setSubtotal(subtotal);
        }
        return itensPedidos;
    }

    //Função para calcular o valor total do pedido de acordo com a soma de todos os subtotais de cada item
    public BigDecimal calcularValorTotal(List<ItensPedidos> itensPedidos){
        return itensPedidos.stream()
                .map(ItensPedidos::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //Função que irá acionar o pagamento e obter retorno de sucesso ou falha
    public PagamentoResponse processarPagamento(Pedido pedido){

        PagamentoRequest pagamentoRequest = new PagamentoRequest(
                calcularValorTotal(pedido.getItensPedidos()),
                pedido.getCartaoCredito(),
                pedido.getId(),
                pedido.getClienteId()
        );
        PagamentoResponse pagamentoResponse = new PagamentoResponse();

        //Logica para processar pagamento

        //Tratar status do pedido caso o cartão não possua saldo

        return pagamentoResponse;

    }

    //Tratar status de acordo com o  momento do pedido
    public void salvarPedido(Pedido pedido){
        createPedidoUseCase.createPedido(pedido);
    }





}
