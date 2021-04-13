CREATE TABLE categoria (
    id serial,
    nome varchar NOT NULL UNIQUE,
    PRIMARY KEY (id)
);