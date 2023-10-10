CREATE TABLE paciente (
    id_paciente bigint NOT NULL AUTO_INCREMENT,
    nombre varchar(55) not NULL,
    apellido varchar(55) not NULL,
    edad int NOT NULL,
    telefono varchar(8) NOT NULL,
    PRIMARY KEY (id_paciente)
   
 )