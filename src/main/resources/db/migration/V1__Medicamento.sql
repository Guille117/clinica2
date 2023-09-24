CREATE TABLE medicamento (
   id_medicamento bigint NOT NULL AUTO_INCREMENT,
   nombre varchar(255) not NULL,
   marca varchar(255) not NULL,
   fecha_ingreso date NOT NULL,
   fecha_vencimiento date NOT NULL,
   can_disponible int NOT NULL,
   precio_unitario double NOT NULL,
   PRIMARY KEY (id_medicamento)
 );
