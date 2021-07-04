create table voto (
    id bigint not null auto_increment,
    filme_id bigint,
    usuario_id bigint,
    data_voto datetime not null,
    nota integer,
    
    primary key (id)
);

alter table voto add constraint fk_voto_filme
foreign key (filme_id) references filme(id);

alter table voto add constraint fk_voto_usuario
foreign key (usuario_id) references usuario(id);
