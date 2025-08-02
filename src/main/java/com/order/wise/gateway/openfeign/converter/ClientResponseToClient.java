package com.order.wise.gateway.openfeign.converter;

import com.order.wise.domain.Client;
import com.order.wise.gateway.openfeign.response.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientResponseToClient {

    public Client convert(ClientResponse clientResponse){
        return new Client(clientResponse.getId(),
                clientResponse.getNome(),
                clientResponse.getCpf(),
                clientResponse.getDataNascimento()
        );
    }
}
