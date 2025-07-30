package com.order.wise.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Client {

    private BigInteger id;
    private String name;
    private String identifier;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate datOfBirth;

}