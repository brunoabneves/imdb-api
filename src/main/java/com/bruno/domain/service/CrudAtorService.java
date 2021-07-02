package com.bruno.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Ator;
import com.bruno.domain.model.Filme;
import com.bruno.domain.repository.AtorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudAtorService {
	
	private AtorRepository atorRepository;
	private CrudFilmeService crudFilmeService;
	
	public Ator buscar(Long atorId) {
		return atorRepository.findById(atorId)
				.orElseThrow(() -> new NegocioException("Ator não encontrado"));
	}
	
	@Transactional
	public Ator registrar(Long filmeId, String nome) {
		Filme filme = crudFilmeService.buscar(filmeId);
		
		return filme.adicionarAtor(nome);
	}
	
	@Transactional
	public void excluir(Long atorId) {
		atorRepository.deleteById(atorId);
	}
	
}
