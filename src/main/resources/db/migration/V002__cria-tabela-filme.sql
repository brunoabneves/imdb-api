create table if not exists filme (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    diretor varchar(60),
    genero varchar(50),
    
    primary key (id)
);