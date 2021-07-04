package com.bruno.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.api.assembler.FilmeAssembler;
import com.bruno.api.model.FilmeModel;
import com.bruno.api.model.input.FilmeInput;
import com.bruno.domain.model.Filme;
import com.bruno.domain.repository.FilmeRepository;
import com.bruno.domain.service.CrudFilmeService;
import com.bruno.domain.service.MediaVotosService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
//@RequestMapping("/filmes")
public class FilmeController {
	
	private FilmeRepository filmeRepository;
	private CrudFilmeService crudFilmeService;
	private FilmeAssembler filmeAssembler;
	private MediaVotosService mediaVotoService;
	
	@GetMapping("/filmes")
	public List<FilmeModel> listar() {
		
		mediaVotoService.preencheMediaVotosNoFindAll();
		return filmeAssembler.toCollectionModel(filmeRepository.findAll());
	}
	
	@GetMapping("/filmes/{filmeId}")
	public ResponseEntity<FilmeModel> buscar(@PathVariable Long filmeId) {
		mediaVotoService.preencheMediaVoto(filmeId);
		return filmeRepository.findById(filmeId)
				.map(filme ->  ResponseEntity.ok(filmeAssembler.toModel(filme)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/filmes/genero/{genero}")
	public List<FilmeModel> listarPorGenero(@PathVariable String genero) {
		return filmeAssembler.toCollectionModel(filmeRepository.findByGenero(genero));

	}
	
	@GetMapping("/filmes/nome/{nome}")
	public List<FilmeModel> listarPorNome(@PathVariable String nome) {
		return filmeAssembler.toCollectionModel(filmeRepository.findByNomeContaining(nome));
	}
	
	@GetMapping("/filmes/diretor/{diretor}")
	public List<FilmeModel> listarPorDiretor(@PathVariable String diretor) {
		return filmeAssembler.toCollectionModel(filmeRepository.findByDiretorContaining(diretor));
	}
	
	@GetMapping("/filmes/ator/{ator}")
	public List<FilmeModel> listarPorAtor(@PathVariable String ator) {
		return crudFilmeService.buscaFilmeByAtor(ator);
	}
	
	@PostMapping("/admin/filmes")
	//@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public FilmeModel cadastrar (@Valid @RequestBody FilmeInput filmeInput) {
		Filme novoFilme = filmeAssembler.toEntity(filmeInput);
		return filmeAssembler.toModel(crudFilmeService.salvar(novoFilme));
	}
}
