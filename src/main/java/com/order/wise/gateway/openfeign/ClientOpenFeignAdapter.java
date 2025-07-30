package com.order.wise.gateway.openfeign;

import com.order.wise.domain.Client;
import com.order.wise.gateway.ClientGateway;
import com.order.wise.gateway.openfeign.converter.ClientResponseToClient;
import com.order.wise.gateway.openfeign.response.ClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientOpenFeignAdapter implements ClientGateway {

    private final ClientOpenFeignClient clientOpenFeignClient;
    private final ClientResponseToClient clientResponseToClient;

    @Override
    public Client getClientByIdentifier(String clientIdentifier) {
        ClientResponse clientResponse = clientOpenFeignClient.getClientByIdentifier(clientIdentifier);
        return clientResponseToClient.convert(clientResponse);
    }

}
