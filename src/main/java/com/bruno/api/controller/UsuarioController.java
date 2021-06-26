package com.bruno.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioRepository;
import com.bruno.domain.service.CrudUsuarioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioRepository usuarioRepository;
	private CrudUsuarioService crudUsuarioService;
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario cadastrar (@Valid @RequestBody Usuario usuario) {
		return crudUsuarioService.salvar(usuario);
	}
	
	@PutMapping("/{usuarioId}")
	public ResponseEntity<Usuario> editar (@Valid @PathVariable Long usuarioId, @RequestBody Usuario usuario) {
		if(!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		
		usuario.setId(usuarioId);
		usuario = crudUsuarioService.salvar(usuario);
		
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> deletar(@PathVariable Long usuarioId) {

		if (!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}

		crudUsuarioService.excluir(usuarioId);

		return ResponseEntity.noContent().build();
	}

}
