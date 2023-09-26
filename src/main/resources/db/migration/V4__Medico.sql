create table medico(
    id_medico bigint not Null AUTO_INCREMENT,
    nombre varchar(55) not null,
    apellido varchar(55) not null,
    edad int not null,
    estado tinyint(1) default 1,
    telefono varchar(8) not null,
    dpi varchar(13) unique not null,
    especialidad varchar(55) not null,
    primary key(id_medico)
);
