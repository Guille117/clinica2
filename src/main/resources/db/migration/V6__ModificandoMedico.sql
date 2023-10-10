alter table medico add column id_especialidad bigint;
alter table medico add constraint fk_medico_especialidad foreign key (id_especialidad) references especialidad (id_especialidad);
alter table medico drop column especialidad;  