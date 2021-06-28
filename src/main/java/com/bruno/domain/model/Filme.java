package com.bruno.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Filme {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Size(max = 60)
	private String diretor;
	
	@NotBlank
	@Size(max = 20)
	private String genero;
	
	@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
	private List<Ator> ator = new ArrayList<>();
	
	public Ator adicionarAtor(String nome) {
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setFilme(this);
		
		this.getAtor().add(ator);
		
		return ator;
	}
	
}
