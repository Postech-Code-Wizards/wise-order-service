CREATE TYPE status AS ENUM ('ABERTO', 'FECHADO_COM_SUCESSO', 'FECHADO_SEM_ESTOQUE', 'FECHADO_SEM_CREDITO');

CREATE TABLE pedidos (
                         id BIGSERIAL PRIMARY KEY,
                         cliente_id BIGSERIAL NOT NULL,
                         data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         status status NOT NULL DEFAULT 'ABERTO',
                         cartao_credito VARCHAR(19) NOT NULL,
                         valor_total DECIMAL(10,2) NOT NULL
);

CREATE TABLE itens_pedidos (
                              id BIGSERIAL PRIMARY KEY,
                              pedido_id BIGINT NOT NULL,
                              sku_produto VARCHAR(50) NOT NULL,
                              nome_produto VARCHAR(100) NOT NULL,
                              quantidade INT NOT NULL CHECK (quantidade > 0),
                              preco_unitario DECIMAL(10,2) NOT NULL,
                              subtotal DECIMAL(10,2) NOT NULL,
                              CONSTRAINT fk_itens_pedidos_pedidos FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE
);


