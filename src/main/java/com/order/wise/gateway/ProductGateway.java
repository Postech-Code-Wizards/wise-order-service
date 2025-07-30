package com.order.wise.gateway;

import com.order.wise.domain.Product;

public interface ProductGateway {

    Product getProductBySKU(String sku);

}