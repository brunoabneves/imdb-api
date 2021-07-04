create table if not exists usuario (
	id bigint not null auto_increment,
    nome varchar(60),
    username varchar(20),
    senha varchar(200),
    administrador boolean,
    ativo boolean,
    
    primary key (id)
);