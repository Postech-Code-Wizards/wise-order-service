CREATE TYPE status AS ENUM ('OPEN', 'CLOSED_SUCCESSFULLY', 'CLOSED_OUT_OF_STOCK', 'CLOSED_NO_CREDIT');

CREATE TABLE tb_order (
                         id BIGSERIAL PRIMARY KEY,
                         client_id BIGINT NOT NULL,
                         date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         status status NOT NULL DEFAULT 'OPEN',
                         credit_card VARCHAR(19) NOT NULL,
                         payment_id BIGINT NULL,
                         total_value DECIMAL(10,2) NOT NULL
);

CREATE TABLE tb_order_item (
                              id BIGSERIAL PRIMARY KEY,
                              order_id BIGINT NOT NULL,
                              product_id BIGINT NOT NULL,
                              sku_product VARCHAR(50) NOT NULL,
                              product_name VARCHAR(100) NOT NULL,
                              quantity INT NOT NULL CHECK (quantity > 0),
                              unit_price DECIMAL(10,2) NOT NULL,
                              subtotal DECIMAL(10,2) NOT NULL,
                              CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES tb_order(id) ON DELETE CASCADE
);


