package com.bruno.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.domain.model.TipoUsuario;
import com.bruno.domain.model.Usuario;

@RestController
public class UsuarioController {
	
	@GetMapping("/usuarios")
	public List<Usuario> listar () {
		var usuario1 = new Usuario();
		usuario1.setId(1L);
		usuario1.setNome("João Das Neves");
		usuario1.setEmail("joaodasneves@email.com");
		usuario1.setTipo(TipoUsuario.COMUM);
		
		var usuario2 = new Usuario();
		usuario2.setId(2L);
		usuario2.setNome("Anakin Skywalker");
		usuario2.setEmail("anakin@email.com");
		usuario2.setTipo(TipoUsuario.ADMINISTRADOR);
		
		return Arrays.asList(usuario1, usuario2);
	}

}
