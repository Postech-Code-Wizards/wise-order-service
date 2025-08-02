package com.order.wise.gateway.openfeign.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ClientResponse {
    private BigInteger id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
}