package com.bruno.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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

	private String nome;

	private String diretor;
	
	private String genero;
	
	@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
	private List<Voto> votos = new ArrayList<>();
	
	@Transient
	private Long mediaVotos;
	
	@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
	private List<Ator> ator = new ArrayList<>();
	
	public Ator adicionarAtor(String nome) {
		Ator ator = new Ator();
		ator.setNome(nome);
		ator.setFilme(this);
		
		this.getAtor().add(ator);
		
		return ator;
	}
	
	public Voto adicionarVoto(Usuario usuario, Long nota) {
		
		Voto voto = new Voto();
		voto.setNota(nota);
		voto.setDataVoto(OffsetDateTime.now());
		voto.setFilme(this);
		voto.setUsuario(usuario);
		
		this.getVotos().add(voto);
		
		return voto;
	}
}