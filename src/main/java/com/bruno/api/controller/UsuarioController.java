package com.bruno.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
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

import com.bruno.api.assembler.UsuarioAssembler;
import com.bruno.api.model.UsuarioModel;
import com.bruno.api.model.input.UsuarioInput;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioPaginadoRepository;
import com.bruno.domain.repository.UsuarioRepository;
import com.bruno.domain.service.CrudUsuarioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class UsuarioController {
	
	private UsuarioRepository usuarioRepository;
	private UsuarioPaginadoRepository usuarioPaginadoRepository;
	private CrudUsuarioService crudUsuarioService;
	private UsuarioAssembler usuarioAssembler;
	
	@GetMapping("/usuarios/paginado")
	public List<UsuarioModel> listarPaginado(Pageable pageable) {
		return usuarioAssembler.toPageModel(usuarioPaginadoRepository.findAll(pageable));
	}
	
	@GetMapping("/usuarios")
	public List<UsuarioModel> listar() {
		return usuarioAssembler.toCollectionModel(usuarioRepository.findAll());
	}
	
	//remover a rota "/admin" para dar permissão ao teste unitário do controller
	@GetMapping("/admin/usuarios/lista-user-comun-ativo")
	public List<UsuarioModel> listarUserComunAnativo(boolean admin, boolean ativo) {
		return usuarioAssembler.toCollectionModel(usuarioRepository.findByAdministradorAndAtivo(false,true));
	}	
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel cadastrar (@Valid @RequestBody UsuarioInput usuarioInput) {
		Usuario novoUsuario = usuarioAssembler.toEntity(usuarioInput);
		return usuarioAssembler.toModel(crudUsuarioService.salvar(novoUsuario));
	}
	
	@PutMapping("/usuarios/{usuarioId}")
	public ResponseEntity<UsuarioModel> editar (@Valid @PathVariable Long usuarioId, @RequestBody UsuarioInput usuarioInput) {
		if(!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		
		Usuario usuarioEditado = usuarioAssembler.toEntity(usuarioInput);
		
		usuarioEditado.setId(usuarioId);
		
		return ResponseEntity.ok(usuarioAssembler.toModel(crudUsuarioService.salvar(usuarioEditado)));
	}
	
	//Exclusão lógica
	@DeleteMapping("/usuarios/{usuarioId}")
	public ResponseEntity<Void> deletar(@PathVariable Long usuarioId) {

		if (!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}

		crudUsuarioService.excluir(usuarioId);

		return ResponseEntity.noContent().build();
	}

}
