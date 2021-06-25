package com.bruno.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.domain.model.Usuario;

@RestController
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping("/usuarios")
	public List<Usuario> listar() {
		return manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

}
