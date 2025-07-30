package com.order.wise.gateway;

import com.order.wise.domain.Client;

public interface ClientGateway {

    Client getClientByIdentifier(String clientIdentifier);

}