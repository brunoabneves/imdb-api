package com.bruno.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@SQLDelete(sql = "update usuario set ativo = 0 where id = ?")
@Where(clause = "ativo = 1")
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	
	@NotBlank
	@Size(max = 20)
	private String username;
	
	@NotBlank
	private String senha;
	
	private boolean administrador;
	
	private boolean ativo;
	
}
