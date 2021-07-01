package com.bruno.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeModel {

	private Long id;
	private String nome;
	//private String nomeAtor;
	private String genero;
	private String diretor;
	private Long mediaVotos;
	
}
