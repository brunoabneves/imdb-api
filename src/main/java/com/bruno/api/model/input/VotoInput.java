package com.bruno.api.model.input;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoInput {

	@Range(max = 4)
	private Long nota;
	
}
