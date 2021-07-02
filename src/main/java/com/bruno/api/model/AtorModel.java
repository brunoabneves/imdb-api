package com.bruno.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtorModel {
	
	private Long id;
	private String nome;
	private FilmeModel filme;
}
