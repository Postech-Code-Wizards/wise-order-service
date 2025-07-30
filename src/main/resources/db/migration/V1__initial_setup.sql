CREATE TYPE status AS enum ('OPEN', 'CLOSED_SUCCESSFULLY', 'CLOSED_OUT_OF_STOCK'
, 'CLOSED_NO_CREDIT', 'CLIENT_NOT_FOUND', 'PRODUCT_NOT_FOUND');

CREATE TABLE tb_order
  (
     id                BIGSERIAL PRIMARY KEY,
     client_identifier VARCHAR(50) NOT NULL,
     client_id         BIGINT NULL,
     date_created      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     status            STATUS NOT NULL DEFAULT 'OPEN',
     credit_card       VARCHAR(19) NOT NULL,
     payment_id        VARCHAR(50) NULL,
     total_value       DECIMAL(10, 2) NULL
  );

CREATE TABLE tb_order_item
  (
     id           BIGSERIAL PRIMARY KEY,
     order_id     BIGINT NOT NULL,
     product_id   BIGINT NULL,
     sku_product  VARCHAR(50) NOT NULL,
     product_name VARCHAR(100) NULL,
     quantity     INT NOT NULL CHECK (quantity > 0),
     unit_price   DECIMAL(10, 2) NULL,
     CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES tb_order(
     id) ON DELETE cascade
  );