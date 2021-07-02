package com.bruno.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.api.assembler.AtorAssembler;
import com.bruno.api.assembler.FilmeAssembler;
import com.bruno.api.model.AtorModel;
import com.bruno.api.model.FilmeModel;
import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Filme;
import com.bruno.domain.repository.AtorRepository;
import com.bruno.domain.repository.FilmeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudFilmeService {

	private FilmeRepository filmeRepository;
	private FilmeAssembler filmeAssembler;
	private AtorAssembler atorAssembler;
	private AtorRepository atorRepository;
	
	@Transactional
	public Filme salvar(Filme filme) {
		boolean nomeEmUso = filmeRepository.findByNome(filme.getNome())
				.stream()
				.anyMatch(filmeExistente -> !filmeExistente.equals(filme));
		
		if (nomeEmUso) {
			throw new NegocioException("Já existe um usuário cadastrado com este nome.");
		}
		
		return filmeRepository.save(filme);
	}
	
	public Filme buscar(Long filmeId) {
		return filmeRepository.findById(filmeId)
				.orElseThrow(() -> new NegocioException("Filme não encontrado"));
	}
	
	public void excluir(Long filmeId) {
		filmeRepository.deleteById(filmeId);
	}
	
	
	public List<FilmeModel> buscaFilmeByAtor(String nome) {
		List<AtorModel> atorModel = atorAssembler.toCollectionModel(atorRepository.findByNomeContaining(nome));
		
		List<FilmeModel> filmeModel = new ArrayList<>();
		List<Long> listaFilmeId = new ArrayList<>();
		
		atorModel.stream().map(n -> listaFilmeId.add(n.getFilme().getId())).collect(Collectors.toList());
		
		for (Long atorId : listaFilmeId) {
			filmeModel = filmeAssembler.toCollectionModel(filmeRepository.findById(atorId));
		}
		
		return filmeModel;
	}
	

	
//	@Transactional
//	public void preencheAtor() {
//		//pega todos os filmes
//		List<FilmeModel> filmes = filmeAssembler.toCollectionModel(filmeRepository.findAll());
//		
//		List<Long> listaId = new ArrayList<>();
//		
//		filmes.stream().map(n -> listaId.add(n.getId())).collect(Collectors.toList());
//		
//		for (Long filmeId : listaId) {
//			setaAtor(filmeId);
//		}
//	}
//	
//	@Transactional
//	public void setaAtor(Long filmeId) {
//		
//		List<AtorModel> atores = atorAssembler.toCollectionModel(atorRepository.findByFilmeId(filmeId));
//		//FilmeModel filmeModel = filmeAssembler.toModel(filmeRepository.findById(filmeId));
//		AtorModel ator = new AtorModel();
//		for (AtorModel atorModel : atores) {
//			
//		}
//	}

}
