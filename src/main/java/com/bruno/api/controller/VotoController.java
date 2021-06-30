package com.bruno.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.domain.model.Voto;
import com.bruno.domain.service.VotarService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/filmes/{filmeId}/{usuarioId}/votar")
public class VotoController {

	private VotarService votarService;
	
	/*método apenas de teste
	@GetMapping
	public List<Voto> listar() {
		return votoRepository.findAll();
	}*/
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Voto cadastrarVoto(@PathVariable Long filmeId, @PathVariable Long usuarioId, @Valid @RequestBody Voto voto) {
		return votarService.votar(filmeId, usuarioId,voto);
	}
	
}
