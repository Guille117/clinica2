CREATE TABLE cobro(
    id_cobro BIGINT NOT NULL auto_increment,
    id_consulta_cobrar BIGINT,
    cantidad_medicamentos INT DEFAULT NULL,
    fecha_cobro DATE NOT NULL,
    id_paciente BIGINT,
    nombre_paciente VARCHAR(75) DEFAULT NULL,
    total DOUBLE NOT NULL,
    PRIMARY KEY(id_cobro),
    FOREIGN KEY(id_consulta_cobrar) REFERENCES consulta (id_consulta)
);