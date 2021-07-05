package com.bruno.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.bruno.domain.model.Ator;

public class AtorCreator {

	@Test
	public static Ator criaAtorASerSalvo() {
		Ator ator = new Ator();
		ator.setNome("Vigo Mortense");
		ator.setFilme(FilmeCreator.criaFilmeASerSalvo());
		
		ator.getFilme().getAtor().add(ator);
		
		return ator;
	}
	
	@Test
	public static Ator criaAtorValido() {
		Ator ator = new Ator();
		ator.setId(1L);
		ator.setNome("Viggo Mortense");
		//ator.setFilme(FilmeCreator.criaFilmeValido());
		
		//ator.getFilme().getAtor().add(ator);
		
		return ator;
	}
	
	@Test
	public static Ator criaAtorValido2() {
		Ator ator = new Ator();
		ator.setId(2L);
		ator.setNome("Orlando Bloom");
		//ator.setFilme(FilmeCreator.criaFilmeValido());
		
		//ator.getFilme().getAtor().add(ator);
		
		return ator;
	}
	
	@Test
	public static Ator criaAtorValido3() {
		Ator ator = new Ator();
		ator.setId(3L);
		ator.setNome("Mark Hamill");
		//ator.setFilme(FilmeCreator.criaFilmeValido());
		
		//ator.getFilme().getAtor().add(ator);
		
		return ator;
	}
	
	@Test
	public static List<Ator> CriaListaDeAtorValido() {
		List<Ator> atores = new ArrayList<>();
		
		atores.add(criaAtorValido());
		atores.add(criaAtorValido2());
		atores.add(criaAtorValido3());
		
		return atores;
	}
	
	@Test
	public static Ator criaAtorAtualizadoValido() {
		Ator ator = new Ator();
		ator.setId(1L);
		ator.setNome("Will Smith");
		ator.setFilme(FilmeCreator.criaFilmeASerSalvo());
		
		ator.getFilme().getAtor().add(ator);
		
		return ator;
	}
}
