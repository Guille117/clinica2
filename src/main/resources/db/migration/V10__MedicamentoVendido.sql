create table medicamento_vendido(
    id_med_vendido bigint auto_increment,
    id_medicamento bigint not null,
    cantidad int not null,
    total double not null,
    PRIMARY KEY(id_med_vendido),
    FOREIGN KEY(id_medicamento) REFERENCES medicamento(id_medicamento)
);