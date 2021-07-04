package com.bruno.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.api.assembler.FilmeAssembler;
import com.bruno.api.model.FilmeModel;
import com.bruno.domain.model.Filme;
import com.bruno.domain.model.Voto;
import com.bruno.domain.repository.FilmeRepository;
import com.bruno.domain.repository.VotoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MediaVotosService {
	
	private VotoRepository votoRepository;
	private CrudFilmeService crudFilmeService;
	private FilmeAssembler  filmeAssembler;
	private FilmeRepository filmeRepository;
	
	public Long mediaVoto(Long filmeId) {
		
		Long somaNotas = 0L;
		List<Long> notas = new ArrayList<>();
		
		List<Voto> votos = votoRepository.findByFilmeId(filmeId);

		//adiciona as notas no array de notas
		votos.stream().map(n -> notas.add(n.getNota())).collect(Collectors.toList());
		
		int size = votos.size();
		
		for(Long n : notas) {
			somaNotas = n + somaNotas;
		}
		
		if(somaNotas > 0L) {
			Long media = somaNotas/size;
			return media;
		}
		
		return 0L;
	}
	
	@Transactional
	public void preencheMediaVoto(Long filmeId) {
		Filme filme = crudFilmeService.buscar(filmeId);
		
		mediaVoto(filmeId);
		
		filme.setMediaVotos(mediaVoto(filmeId));
	}
	
	public void preencheMediaVotosNoFindAll() {
		
		List<FilmeModel> filmes = filmeAssembler.toCollectionModel(filmeRepository.findAll());
		
		List<Long> listaId = new ArrayList<>();
		
		//pega os ids de todos os filmes e salva no arrya listasId
		filmes.stream().map(n -> listaId.add(n.getId())).collect(Collectors.toList());
		
		for (Long filmeId : listaId) {
			preencheMediaVoto(filmeId);
		}
	}
}