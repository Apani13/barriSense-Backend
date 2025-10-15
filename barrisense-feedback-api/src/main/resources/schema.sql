-- Crea la tabla de barrios para que data.sql pueda insertar datos en ella
CREATE TABLE barrios (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    district VARCHAR(255)
);

-- Crea la tabla para la relación de códigos postales de la entidad Neighborhood
CREATE TABLE neighborhood_postal_codes (
    neighborhood_id BIGINT NOT NULL,
    postal_code BIGINT,
    FOREIGN KEY (neighborhood_id) REFERENCES barrios(id)
);