-- Corrección Opción A para el arranque de la Práctica 2 (Hito 01).
-- Ya colocado en src/main/resources/schema.sql; Spring lo corre
-- antes de la validación de Hibernate (ver application.properties).
-- Solo usa campos verificados en las entidades del kit (2026-11-13):
-- Cliente: id; Poliza: id + tipo (0 Auto, 1 Vida, 2 Médico);
-- Beneficiario: id + poliza (@ManyToOne, columna poliza_id).
-- Nada inventado: el resto de atributos sigue como TODO en las entidades.
CREATE TABLE IF NOT EXISTS cliente (
    id BIGSERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS poliza (
    id BIGSERIAL PRIMARY KEY,
    tipo INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS beneficiario (
    id BIGSERIAL PRIMARY KEY,
    poliza_id BIGINT REFERENCES poliza (id)
);
