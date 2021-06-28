package com.bruno.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;

@Entity
public class Administrador {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
		
	@NotBlank
	@Size(max = 60)
	protected String nome;
		
	@NotBlank
	@Email
	@Size(max = 255)
	protected String email;
		
	@Size(max = 100)
	protected String senha;
}
