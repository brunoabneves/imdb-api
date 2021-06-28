package com.bruno.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Filme;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.model.Voto;
import com.bruno.domain.repository.VotoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VotarService {

	private VotoRepository votoRepository;
	private CrudFilmeService crudFilmeService;
	private CrudUsuarioService crudUsuarioService;
	
	@Transactional
	public Voto salvar(Voto voto) {
		boolean filmeExiste = votoRepository.findByFilme(voto.getFilme().getId())
				.stream()
				.anyMatch(votoExistente -> !votoExistente.equals(voto));
		
		boolean usuarioExiste = votoRepository.findByUsuario(voto.getUsuario().getId())
				.stream()
				.anyMatch(votoExistente -> !votoExistente.equals(voto));
		
		if (filmeExiste && usuarioExiste) {
			throw new NegocioException("Este usuário já  votou neste filme.");
		}
		
		return votoRepository.save(voto);
	}
	
	@Transactional
	public Voto votar(Long usuarioId, Long filmeId, Integer nota) {
		
		Voto voto = new Voto();
		Usuario usuario = crudUsuarioService.buscar(usuarioId);
		Filme filme = crudFilmeService.buscar(filmeId);
		
		voto.setUsuario(usuario);
		voto.setNota(nota);
		voto.setFilme(filme);
		voto.setDataVoto(OffsetDateTime.now());
		
		return voto;
	}
}
