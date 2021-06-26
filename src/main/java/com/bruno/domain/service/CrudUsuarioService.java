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
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		boolean emailEmUso = usuarioRepository.findByEmail(usuario.getEmail())
				.stream()
				.anyMatch(usuarioExistente -> !usuarioExistente.equals(usuario));
		
		if (emailEmUso) {
			throw new NegocioException("Já existe um usuário cadastrado com este e-mail.");
		}
		
		return usuarioRepository.save(usuario);
	}
	
	//implementar exclusão lógica aqui
	@Transactional
	public void excluir(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}
}
