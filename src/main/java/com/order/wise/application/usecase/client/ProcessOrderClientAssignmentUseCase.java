package com.order.wise.application.usecase.client;

import com.order.wise.domain.Client;
import com.order.wise.domain.Order;
import com.order.wise.exceptions.ClientNotFoundException;
import com.order.wise.gateway.ClientGateway;
import com.order.wise.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.order.wise.domain.enums.StatusEnum.CLIENT_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessOrderClientAssignmentUseCase {

    private final ClientGateway clientGateway;
    private final OrderGateway orderGateway;

    public void execute(Order order) {

        try {
            Client client = clientGateway.getClientByIdentifier(order.getClientIdentifier());
            orderGateway.updateClientId(order.getId(), client.getId());
        } catch (Exception e) {
            orderGateway.updateStatus(order.getId(), CLIENT_NOT_FOUND);
            log.error("Error finding client for order: {}", order, e);
            throw new ClientNotFoundException("Client not found for order: " + order.getId(), e);
        }

    }

}
