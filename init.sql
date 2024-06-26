-- Instruções para criação de tabelas e inserção de dados
CREATE TABLE IF NOT EXISTS clientes (
    id SERIAL PRIMARY KEY,
    limite INTEGER NOT NULL,
    saldo INTEGER NOT NULL
);
INSERT INTO clientes (limite, saldo) VALUES
    (100000, 0),
    (80000, 0),
    (1000000, 0),
    (10000000, 0),
    (500000, 0)
ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS transacoes (
    id SERIAL PRIMARY KEY,
    valor INTEGER NOT NULL,
    tipo VARCHAR(1) NOT NULL,
    descricao VARCHAR(10) NOT NULL,
    realizada_em TIMESTAMP NOT NULL,
    cliente_id INTEGER NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE INDEX IF NOT EXISTS idx_cliente_id ON transacoes (cliente_id);

GRANT ALL PRIVILEGES ON DATABASE rinha TO postgres;
