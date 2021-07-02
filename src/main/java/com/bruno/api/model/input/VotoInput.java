package com.bruno.api.model.input;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoInput {

	@NotNull
	@Range(max = 4)
	private Long nota;
	
}
