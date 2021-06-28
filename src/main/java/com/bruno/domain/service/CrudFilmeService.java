package com.bruno.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Filme;
import com.bruno.domain.repository.FilmeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudFilmeService {

	private FilmeRepository filmeRepository;
	
	@Transactional
	public Filme salvar(Filme filme) {
		boolean nomeEmUso = filmeRepository.findByNome(filme.getNome())
				.stream()
				.anyMatch(filmeExistente -> !filmeExistente.equals(filme));
		
		if (nomeEmUso) {
			throw new NegocioException("Já existe um usuário cadastrado com este e-mail.");
		}
		
		return filmeRepository.save(filme);
	}
	
	public Filme buscar(Long filmeId) {
		return filmeRepository.findById(filmeId)
				.orElseThrow(() -> new NegocioException("Filme não encontrado"));
	}
}
