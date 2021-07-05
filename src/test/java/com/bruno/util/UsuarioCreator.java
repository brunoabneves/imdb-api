package com.bruno.util;

import org.junit.jupiter.api.Test;

import com.bruno.domain.model.Usuario;

public class UsuarioCreator {

	@Test
	public static Usuario criaUsuarioASerSalvo() {
		Usuario usuario = new Usuario();
		usuario.setNome("Anakin Skywalker");
		usuario.setUsername("darthVader");
		usuario.setSenha("executeOrder66");
		usuario.setAdministrador(false);
		usuario.setAtivo(true);
		
		return usuario;
	}
	
	@Test
	public static Usuario criaUsuarioValido() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Anakin Skywalker");
		usuario.setUsername("darthVader");
		usuario.setSenha("executeOrder66");
		usuario.setAdministrador(false);
		usuario.setAtivo(true);
		
		return usuario;
	}
	
	@Test
	public static Usuario criaUsuarioAtualizadoValido() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Eren Yeager");
		usuario.setUsername("titadeataque");
		usuario.setSenha("tatakae");
		usuario.setAdministrador(true);
		usuario.setAtivo(true);
		
		return usuario;
	}
	
}
