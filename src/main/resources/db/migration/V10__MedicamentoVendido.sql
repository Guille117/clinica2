create table medicamento_vendido(
    id_venta bigint auto_increment,
    id_medicamento bigint not null,
    nombre_Medicamento varchar(60) not null,
    marca_medicamento varchar(60) not null,
    fecha_vencimiento_medicamento date not null,
    precio_medicamento double not null,
    fecha_venta date not null,
    cantidad int not null,
    total double not null,
    PRIMARY KEY(id_venta)
);