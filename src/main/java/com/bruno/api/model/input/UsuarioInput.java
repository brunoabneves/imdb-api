package com.bruno.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Size(max = 20)
	private String username;
	
	@NotBlank
	private String senha;
	
	private boolean administrador;
	
	private boolean ativo = true;
	
}
