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
import com.bruno.domain.model.Voto;
import com.bruno.domain.repository.AtorRepository;
import com.bruno.domain.repository.FilmeRepository;
import com.bruno.domain.repository.VotoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudFilmeService {

	private FilmeRepository filmeRepository;
	private FilmeAssembler filmeAssembler;
	private VotoRepository votoRepository;
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
	
	@Transactional
	public void mediaVoto(Long filmeId) {
		Filme filme = buscar(filmeId);
		
		mediaFilme(filmeId);
		
		filme.setMediaVotos(mediaFilme(filmeId));
	}
	
	/*public Filme buscarPorAtor(String ator) {
		return filmeRepository.findByAtor(ator)
				.orElseThrow(() -> new NegocioException("Filme não encontrado"));
	}*/
	
	public void excluir(Long filmeId) {
		filmeRepository.deleteById(filmeId);
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
	
	public void adicionaMediVotos() {
		
		List<FilmeModel> filmes = filmeAssembler.toCollectionModel(filmeRepository.findAll());
		
		List<Long> listaId = new ArrayList<>();
		
		//pega os ids de todos os filmes e salva no arrya listasId
		filmes.stream().map(n -> listaId.add(n.getId())).collect(Collectors.toList());
		
		for (Long filmeId : listaId) {
			mediaVoto(filmeId);
		}
	}

}
