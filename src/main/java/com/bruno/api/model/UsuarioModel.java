package com.bruno.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

	private Long id;
	private String nome;
	private String username;
	//private String senha;
	private boolean administrador;
	
}
