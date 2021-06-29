package com.bruno.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeInput {

	@Valid
	@NotNull
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	//private AtorInput nomeAtor;
	
	@NotNull
	@NotBlank
	@Size(max = 20)
	private String genero;
	
	@Valid
	@NotNull
	@NotBlank
	@Size(max = 60)
	private String diretor;
	
}
