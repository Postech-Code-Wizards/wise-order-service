package com.order.wise.gateway.openfeign.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class GraphQLRequest {
    private String query;
    private Map<String, Object> variables;
    private String operationName;
}
