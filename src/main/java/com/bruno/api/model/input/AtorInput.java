package com.bruno.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtorInput {

	@NotBlank
	@Size(max = 60)
	private String nome;
	
}
