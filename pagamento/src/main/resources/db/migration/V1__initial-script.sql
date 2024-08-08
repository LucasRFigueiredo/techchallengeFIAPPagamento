CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(11) UNIQUE,
    email VARCHAR(255) UNIQUE
);


CREATE TABLE Produto (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(255),
    nome VARCHAR(255),
    descricao TEXT,
    preco DOUBLE PRECISION
);

CREATE TABLE Pedido (
    id SERIAL PRIMARY KEY,
    cliente_id SERIAL REFERENCES Cliente(id),
    status VARCHAR(255)
);

CREATE TABLE Pedido_Produto (
    pedido_id SERIAL REFERENCES Pedido(id),
    produto_id SERIAL REFERENCES Produto(id),
    PRIMARY KEY (pedido_id, produto_id)
);

CREATE TABLE Checkout (
    id SERIAL PRIMARY KEY,
    Pedido_id BIGINT,
    total DECIMAL(10, 2),
    pagamento VARCHAR(255),
    status VARCHAR(255),
    FOREIGN KEY (Pedido_id) REFERENCES Pedido(id)
);

CREATE SEQUENCE cliente_seq
INCREMENT 1
START 1;

CREATE SEQUENCE produto_seq
INCREMENT 1
START 1;

CREATE SEQUENCE pedido_seq
INCREMENT 1
START 1;

CREATE SEQUENCE checkout_seq
INCREMENT 1
START 1;