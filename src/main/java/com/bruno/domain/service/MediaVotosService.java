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
	
	private FilmeAssembler filmeAssembler;
	private VotoRepository votoRepository;
	private FilmeRepository filmeRepository;
	private CrudFilmeService crudFilmeService;

	public void adicionaMediaVotos() {
		
		List<FilmeModel> filmes = filmeAssembler.toCollectionModel(filmeRepository.findAll());
		
		List<Long> listaId = new ArrayList<>();
		
		//pega os ids de todos os filmes e salva no arrya listasId
		filmes.stream().map(n -> listaId.add(n.getId())).collect(Collectors.toList());
		
		for (Long filmeId : listaId) {
			mediaVoto(filmeId);
		}
	}
	
	public Long mediaFilme(Long filmeId) {
		List<Voto> votos = votoRepository.findAllByFilmeId(filmeId);
		Long valorTotal = 0L;
		
		int size = votos.size();
		
		for(int i =0; i< size; i++){		
			valorTotal = votos.get(i).getNota() + valorTotal;
		}
		
		if(valorTotal > 0L) {
			Long media = valorTotal/size;
			return media;
		}
		
		return 0L;
	}
	
	@Transactional
	public void mediaVoto(Long filmeId) {
		Filme filme = crudFilmeService.buscar(filmeId);
		
		mediaFilme(filmeId);
		
		filme.setMediaVotos(mediaFilme(filmeId));
	}
	
}
