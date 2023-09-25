CREATE TABLE paciente (
    id_paciente bigint NOT NULL AUTO_INCREMENT,
    nombre varchar(55) not NULL,
    apellido varchar(55) not NULL,
    edad int NOT NULL,
    dpi varchar(13) unique DEFAULT NULL,
    telefono varchar(8) DEFAULT NULL,
    persona_responsable bigint DEFAULT NULL,
    PRIMARY KEY (id_paciente),
    FOREIGN KEY (persona_responsable) REFERENCES persona_responsable (id_responsable)
   
 )