package com.bruno.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.api.assembler.FilmeAssembler;
import com.bruno.api.model.FilmeModel;
import com.bruno.api.model.input.FilmeInput;
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
	private FilmeAssembler filmeAssembler;
	
	@GetMapping
	public List<FilmeModel> listar() {
		return filmeAssembler.toCollectionModel(filmeRepository.findAll());
	}
	
	@GetMapping("/genero/{genero}")
	public ResponseEntity<FilmeModel> listarPorGenero(@PathVariable String genero) {
		return filmeRepository.findByGenero(genero)
				.map(filme ->  ResponseEntity.ok(filmeAssembler.toModel(filme)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<FilmeModel> listarPorNome(@PathVariable String nome) {
		return filmeRepository.findByNomeContaining(nome)
				.map(filme ->  ResponseEntity.ok(filmeAssembler.toModel(filme)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/diretor/{diretor}")
	public ResponseEntity<FilmeModel> listarPorDiretor(@PathVariable String diretor) {
		return filmeRepository.findByDiretorContaining(diretor)
				.map(filme ->  ResponseEntity.ok(filmeAssembler.toModel(filme)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	/*@GetMapping("/ator/{ator}")
	public List<Filme> listarPorAtor(@PathVariable String ator) {
		return filmeRepository.findByAtorContaining(ator);
	}*/
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public FilmeModel cadastrar (@Valid @RequestBody FilmeInput filmeInput) {
		Filme novoFilme = filmeAssembler.toEntity(filmeInput);
		return filmeAssembler.toModel(crudFilmeService.salvar(novoFilme));
	}
}
