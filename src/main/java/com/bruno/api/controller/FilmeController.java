package com.bruno.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public List<Filme> listarPorGenero(@PathVariable String genero) {
		return filmeRepository.findByGenero(genero);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Filme> listarPorNome(@PathVariable String nome) {
		return filmeRepository.findByNomeContaining(nome);
	}
	
	@GetMapping("/diretor/{diretor}")
	public List<Filme> listarPorDiretor(@PathVariable String diretor) {
		return filmeRepository.findByDiretorContaining(diretor);
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
	
	@PutMapping("/{filmeId}")
	public ResponseEntity<Filme> editar (@Valid @PathVariable Long filmeId, @RequestBody Filme filme) {
		if(!filmeRepository.existsById(filmeId)) {
			return ResponseEntity.notFound().build();
		}
		
		filme.setId(filmeId);
		filme = crudFilmeService.salvar(filme);
		
		return ResponseEntity.ok(filme);
	}
	
	@DeleteMapping("/{filmeId}")
	public ResponseEntity<Void> deletar(@PathVariable Long filmeId) {

		if (!filmeRepository.existsById(filmeId)) {
			return ResponseEntity.notFound().build();
		}

		crudFilmeService.excluir(filmeId);

		return ResponseEntity.noContent().build();
	}
}
