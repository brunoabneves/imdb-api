package com.bruno.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.domain.exception.NegocioException;
import com.bruno.domain.model.Filme;
import com.bruno.domain.model.Voto;
import com.bruno.domain.repository.FilmeRepository;
import com.bruno.domain.repository.VotoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudFilmeService {

	private FilmeRepository filmeRepository;
	private VotoRepository votoRepository;
	
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

}
