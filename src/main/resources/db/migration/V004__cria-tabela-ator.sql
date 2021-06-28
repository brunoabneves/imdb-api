create table ator (
	id bigint not null auto_increment,
    filme_id bigint not null,
    nome varchar(60) not null,
    
    primary key (id)
);

alter table ator add constraint fk_ator_filme foreign key (filme_id) references filme (id);