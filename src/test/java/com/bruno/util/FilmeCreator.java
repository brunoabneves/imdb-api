package com.bruno.util;

import com.bruno.domain.model.Filme;

public class FilmeCreator {

	public static Filme criaFilmeASerSalvo() {
		Filme filme = new Filme();
		filme.setNome("Liga da Justiça");
		filme.setDiretor("Zack Snyder");
		filme.setGenero("Héroi");
		
		return filme;
	}
	
	public static Filme criaFilmeValido() {
		Filme filme = new Filme();
		filme.setId(2L);
		filme.setNome("Liga da Justiça");
		filme.setDiretor("Zack Snyder");
		filme.setGenero("Héroi");
		
		return filme;
	}
	
	public static Filme criaFilmeAtualizadoValido() {
		Filme filme = new Filme();
		filme.setId(2L);
		filme.setNome("Interestelar");
		filme.setDiretor("Christopher Nolan");
		filme.setGenero("Ficção Científica");
		
		return filme;
	}
	
}
