DROP TABLE clientes IF EXISTS;
CREATE TABLE clientes  (
    nome VARCHAR(255),
    sobrenome VARCHAR(255),
    dtnascimento VARCHAR(12),
    idade NUMERIC
);