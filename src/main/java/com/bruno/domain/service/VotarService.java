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
	public Voto votar(Long filmeId, Long usuarioId, Voto voto) {
		
		boolean votoExiste = votoRepository.findByFilmeIdAndUsuarioId(filmeId, usuarioId)
				.stream()
				.anyMatch(votoExistente -> !votoExistente.equals(voto));
		
		if (votoExiste) {
			throw new NegocioException("Este usuário já  votou neste filme.");
		}
		
		Voto novoVoto = preencheVoto(filmeId, usuarioId,voto);
		
		return votoRepository.save(novoVoto);
	}
	
	public Voto preencheVoto(Long filmeId, Long usuarioId, Voto voto) {
		Usuario usuario = crudUsuarioService.buscar(usuarioId);
		Filme filme = crudFilmeService.buscar(filmeId);
				
		voto.setUsuario(usuario);
		voto.setFilme(filme);
		voto.setDataVoto(OffsetDateTime.now());
		
		return voto;
	}
	
	public Long limiteNota(Long nota) {
		if (nota >=0 && nota <=4) {
			throw new NegocioException("Nota inválida, insira um número entre 0 e 4.");
		}else
		
		return nota;
	}
}
