package com.order.wise.domain;

import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemTest {

    @ParameterizedTest
    @CsvSource({
            "10.50, 2",
            "0.00, 3",
            "1234.56, 5",
            "2.25, 1000"
    })
    @DisplayName("Must correctly calculate subtotal for positive quantity and unit price")
    void calculateSubtotal_positiveValuesTest(BigDecimal unitPrice, Integer quantity) {
        OrderItem orderItem = Instancio.of(OrderItem.class)
                .set(Select.field("unitPrice"), unitPrice)
                .set(Select.field("quantity"), quantity)
                .create();

        BigDecimal expectedSubtotal = unitPrice.multiply(new BigDecimal(quantity));
        BigDecimal actualSubtotal = orderItem.calculateSubtotal();
        assertEquals(expectedSubtotal, actualSubtotal);
    }

}