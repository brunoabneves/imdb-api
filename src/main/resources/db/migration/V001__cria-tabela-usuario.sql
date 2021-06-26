create table usuario (
	id bigint not null auto_increment,
	nome varchar(60) not null,
    email varchar(255) not null,
    senha varchar(100),
    
    primary key (id)
);