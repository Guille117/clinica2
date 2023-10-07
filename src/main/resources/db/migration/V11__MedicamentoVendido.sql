    create table medicamento_vendido(
    id_venta_medicamento bigint auto_increment,
    id_medicamento bigint not null,
    nombre_Medicamento varchar(60) not null,
    marca_medicamento varchar(60) not null,
    fecha_vencimiento_medicamento date not null,
    precio_medicamento double not null,
    cantidad int not null,
    fecha_venta date not null,
    id_venta_general bigint not null,
    total double not null,
    PRIMARY KEY(id_venta_medicamento),
    foreign KEY(id_venta_general) REFERENCES cobro(id_cobro)
);