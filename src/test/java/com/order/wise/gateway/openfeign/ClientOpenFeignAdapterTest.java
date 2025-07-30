package com.order.wise.gateway.openfeign;

import com.order.wise.domain.Client;
import com.order.wise.gateway.openfeign.converter.ClientResponseToClient;
import com.order.wise.gateway.openfeign.response.ClientResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientOpenFeignAdapterTest {

    @Mock
    private ClientOpenFeignClient clientOpenFeignClient;
    @Mock
    private ClientResponseToClient clientResponseToClient;

    @InjectMocks
    private ClientOpenFeignAdapter clientOpenFeignAdapter;

    @Test
    @DisplayName("Should retrieve client by identifier using OpenFeign client and convert response")
    void getClientByIdentifierTest() {
        String clientIdentifier = Instancio.create(String.class);
        ClientResponse mockClientResponse = Instancio.create(ClientResponse.class);
        Client expectedClient = Instancio.create(Client.class);

        when(clientOpenFeignClient.getClientByIdentifier(clientIdentifier)).thenReturn(mockClientResponse);
        when(clientResponseToClient.convert(mockClientResponse)).thenReturn(expectedClient);

        Client resultClient = clientOpenFeignAdapter.getClientByIdentifier(clientIdentifier);

        verify(clientOpenFeignClient).getClientByIdentifier(clientIdentifier);
        verify(clientResponseToClient).convert(mockClientResponse);

        assertEquals(expectedClient, resultClient);
    }
}