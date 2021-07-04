package com.bruno.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudUsuarioService {

	private UsuarioRepository usuarioRepository;
	
	public Usuario buscar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new NegocioException("Usuário não encontrado"));
	}
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		boolean usernameEmUso = usuarioRepository.findByUsername(usuario.getUsername())
				.stream()
				.anyMatch(usuarioExistente -> !usuarioExistente.equals(usuario));
		
		if (usernameEmUso) {
			throw new NegocioException("Já existe um usuário cadastrado com este username.");
		}
		
		return usuarioRepository.save(usuario);
	}
	

	@Transactional
	public void excluir(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}
}
