CREATE TABLE IF NOT EXISTS autor(
    id serial PRIMARY KEY,
    nome varchar NOT NULL,
    email varchar NOT NULL,
    descricao varchar(400) NOT NULL,
    instante_criacao timestamp NOT NULL
);