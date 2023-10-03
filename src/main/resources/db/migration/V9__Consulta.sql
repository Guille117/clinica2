create table consulta(
    id_consulta bigint AUTO_INCREMENT,
    id_medico bigint not null,
    id_paciente bigint not null,
    id_especialidad bigint not null,
    fecha_y_hora datetime not null,
    pagado tinyint default 0,
    PRIMARY KEY(id_consulta),
    FOREIGN KEY(id_medico) REFERENCES medico (id_medico),
    FOREIGN KEY(id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY(id_especialidad) REFERENCES especialidad(id_especialidad)
);