create table if not exists ator (
	id bigint not null auto_increment,
    filme_id bigint not null,
    nome varchar(60),
    
    primary key(id),
    constraint fk_ator_filme foreign key(filme_id) references filme (id)
);