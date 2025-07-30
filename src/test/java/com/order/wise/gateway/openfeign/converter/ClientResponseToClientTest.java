package com.order.wise.gateway.openfeign.converter;

import com.order.wise.domain.Client;
import com.order.wise.gateway.openfeign.response.ClientResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientResponseToClientTest {

    private final ClientResponseToClient clientResponseToClient = new ClientResponseToClient();

    @Test
    @DisplayName("Should correctly convert ClientResponse to Client domain object")
    void convertTest() {
        ClientResponse clientResponse = Instancio.create(ClientResponse.class);

        Client client = clientResponseToClient.convert(clientResponse);

        assertNotNull(client);
        assertEquals(clientResponse.getId(), client.getId());
        assertEquals(clientResponse.getNome(), client.getName());}

    @Test
    @DisplayName("Should handle null ClientResponse fields gracefully")
    void convert_nullFieldsTest() {

        ClientResponse clientResponse = Instancio.of(ClientResponse.class)
                .set(org.instancio.Select.field("id"), null)
                .set(org.instancio.Select.field("nome"), null)
                .set(org.instancio.Select.field("cpf"), null)
                .set(org.instancio.Select.field("dataNascimento"), null)
                .create();

        Client client = clientResponseToClient.convert(clientResponse);

        assertNotNull(client);
        assertNull(client.getId());
        assertNull(client.getName());
    }
}