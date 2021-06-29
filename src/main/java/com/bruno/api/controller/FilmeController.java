package com.bruno.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.api.model.FilmeModel;
import com.bruno.domain.model.Filme;
import com.bruno.domain.repository.FilmeRepository;
import com.bruno.domain.service.CrudFilmeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/filmes")
public class FilmeController {
	
	private FilmeRepository filmeRepository;
	private CrudFilmeService crudFilmeService;
	
	@GetMapping
	public List<Filme> listar() {
		return filmeRepository.findAll();
	}
	
	@GetMapping("/genero/{genero}")
	public ResponseEntity<FilmeModel> listarPorGenero(@PathVariable String genero) {
		return filmeRepository.findByGenero(genero)
				.map(filme ->{
					FilmeModel filmeModel = new FilmeModel();
					filmeModel.setId(filme.getId());
					filmeModel.setNome(filme.getNome());
					filmeModel.setDiretor(filme.getDiretor());
					filmeModel.setGenero(filme.getGenero());
					
					return ResponseEntity.ok(filmeModel);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<FilmeModel> listarPorNome(@PathVariable String nome) {
		return filmeRepository.findByNomeContaining(nome)
				.map(filme ->{
					FilmeModel filmeModel = new FilmeModel();
					filmeModel.setId(filme.getId());
					filmeModel.setNome(filme.getNome());
					filmeModel.setDiretor(filme.getDiretor());
					filmeModel.setGenero(filme.getGenero());
					
					return ResponseEntity.ok(filmeModel);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/diretor/{diretor}")
	public ResponseEntity<FilmeModel> listarPorDiretor(@PathVariable String diretor) {
		return filmeRepository.findByDiretorContaining(diretor)
				.map(filme ->{
					FilmeModel filmeModel = new FilmeModel();
					filmeModel.setId(filme.getId());
					filmeModel.setNome(filme.getNome());
					filmeModel.setDiretor(filme.getDiretor());
					filmeModel.setGenero(filme.getGenero());
					
					return ResponseEntity.ok(filmeModel);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	/*@GetMapping("/ator/{ator}")
	public List<Filme> listarPorAtor(@PathVariable String ator) {
		return filmeRepository.findByAtorContaining(ator);
	}*/
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Filme cadastrar (@Valid @RequestBody Filme filme) {
		return crudFilmeService.salvar(filme);
	}
}
