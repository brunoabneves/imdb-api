create table voto (
	id bigint not null auto_increment,
    usuario_id bigint not null,
    filme_id bigint not null,
    data_voto datetime not null,
    
    primary key (id)
);

alter table voto add constraint fk_voto_usuario foreign key (usuario_id) references usuario (id);
alter table voto add constraint fk_voto_filme foreign key (filme_id) references filme (id);