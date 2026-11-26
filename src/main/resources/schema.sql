-- Corrección Opción A para el arranque de la Práctica 2 (Hito 01).
-- Ya colocado en src/main/resources/schema.sql; Spring lo corre
-- antes de la validación de Hibernate (ver application.properties).
-- Columnas según las entidades del kit, con los atributos confirmados
-- en la ERS (glosario 3.2.3-3.2.5), los 9 casos de uso y la
-- documentación técnica del Sistema Dueño (2026-11-26):
-- Cliente: id + nombres, primer_apellido, segundo_apellido, direccion,
--   curp, fecha_nacimiento. Poliza: id + tipo (0 Auto, 1 Vida, 2 Médico),
--   clave, monto_asegurado, descripcion, cliente_id (@ManyToOne).
-- Beneficiario: id + poliza_id (@ManyToOne), nombres, primer_apellido,
--   segundo_apellido, fecha_nacimiento, porcentaje_beneficio.
-- Los tipos y nombres de columna siguen el mapeo por defecto de
-- Hibernate/Spring (snake_case), para que la validación coincida.
-- Todas las columnas nuevas admiten NULL: los registros ya guardados
-- (solo id/tipo) siguen siendo válidos y las pruebas 3.4 no se rompen.
-- Las sentencias ALTER TABLE ... ADD COLUMN IF NOT EXISTS actualizan
-- las tablas que ya existen en la base local (creadas con el esquema
-- anterior, sin estos campos): al arrancar, schema.sql agrega las
-- columnas que falten antes de la validación de Hibernate, sin
-- borrar los datos guardados.
CREATE TABLE IF NOT EXISTS cliente (
    id BIGSERIAL PRIMARY KEY,
    nombres VARCHAR(255),
    primer_apellido VARCHAR(255),
    segundo_apellido VARCHAR(255),
    direccion VARCHAR(255),
    curp VARCHAR(255),
    fecha_nacimiento DATE
);

CREATE TABLE IF NOT EXISTS poliza (
    id BIGSERIAL PRIMARY KEY,
    tipo INTEGER NOT NULL,
    clave VARCHAR(255),
    monto_asegurado NUMERIC(19,2),
    descripcion VARCHAR(255),
    cliente_id BIGINT REFERENCES cliente (id)
);

CREATE TABLE IF NOT EXISTS beneficiario (
    id BIGSERIAL PRIMARY KEY,
    poliza_id BIGINT REFERENCES poliza (id),
    nombres VARCHAR(255),
    primer_apellido VARCHAR(255),
    segundo_apellido VARCHAR(255),
    fecha_nacimiento DATE,
    porcentaje_beneficio INTEGER
);

ALTER TABLE cliente ADD COLUMN IF NOT EXISTS nombres VARCHAR(255);
ALTER TABLE cliente ADD COLUMN IF NOT EXISTS primer_apellido VARCHAR(255);
ALTER TABLE cliente ADD COLUMN IF NOT EXISTS segundo_apellido VARCHAR(255);
ALTER TABLE cliente ADD COLUMN IF NOT EXISTS direccion VARCHAR(255);
ALTER TABLE cliente ADD COLUMN IF NOT EXISTS curp VARCHAR(255);
ALTER TABLE cliente ADD COLUMN IF NOT EXISTS fecha_nacimiento DATE;
ALTER TABLE poliza ADD COLUMN IF NOT EXISTS clave VARCHAR(255);
ALTER TABLE poliza ADD COLUMN IF NOT EXISTS monto_asegurado NUMERIC(19,2);
ALTER TABLE poliza ADD COLUMN IF NOT EXISTS descripcion VARCHAR(255);
ALTER TABLE poliza ADD COLUMN IF NOT EXISTS cliente_id BIGINT REFERENCES cliente (id);
ALTER TABLE beneficiario ADD COLUMN IF NOT EXISTS nombres VARCHAR(255);
ALTER TABLE beneficiario ADD COLUMN IF NOT EXISTS primer_apellido VARCHAR(255);
ALTER TABLE beneficiario ADD COLUMN IF NOT EXISTS segundo_apellido VARCHAR(255);
ALTER TABLE beneficiario ADD COLUMN IF NOT EXISTS fecha_nacimiento DATE;
ALTER TABLE beneficiario ADD COLUMN IF NOT EXISTS porcentaje_beneficio INTEGER;
