package com.bruno.api.model;

import java.time.OffsetDateTime;

import com.bruno.domain.model.Filme;
import com.bruno.domain.model.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoModel {
	private Long id;
	private Long nota;
	private Usuario usuario;
	private Filme filme;
	private OffsetDateTime dataVoto;
}
