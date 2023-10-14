CREATE TABLE usuario(
    id_usuario BIGINT AUTO_INCREMENT,
    alias VARCHAR(50) NOT NULL,
    correo VARCHAR(250) NOT NULL UNIQUE,
    contraseña VARCHAR(400) NOT NULL,
    id_funcion BIGINT NOT NULL,
    PRIMARY KEY(id_usuario),
    FOREIGN KEY(id_funcion) REFERENCES funcion(id_funcion)
);
