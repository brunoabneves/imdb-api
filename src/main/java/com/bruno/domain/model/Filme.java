package com.bruno.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filme {
	
	private Long id;
	private String nome;
	private String diretor;
	private String genero;
	private String atores;
	private Long quantidadeVotos;
}
