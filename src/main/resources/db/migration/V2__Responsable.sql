CREATE TABLE persona_responsable (
   id_responsable bigint NOT NULL AUTO_INCREMENT,
   nombre varchar(255) not NULL,
   apellido varchar(255) not NULL,
   edad int NOT NULL,
   telefono varchar(8) DEFAULT NULL,
   dpi varchar(13) DEFAULT NULL,
   PRIMARY KEY (id_responsable)
 )
