package com.bruno.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoModel {
	
	private Long id;
	private Long nota;
	private OffsetDateTime dataVoto;
}
