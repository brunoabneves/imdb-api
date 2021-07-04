package com.bruno.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.api.assembler.VotoAssembler;
import com.bruno.api.model.VotoModel;
import com.bruno.api.model.input.VotoInput;
import com.bruno.domain.model.Voto;
import com.bruno.domain.service.VotarService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/protected/{filmeId}/{usuarioId}/voto")
public class VotoController {

	private VotarService votarService;
	private VotoAssembler votoAssembler;
	
	/*método apenas de teste
	@GetMapping
	public List<Voto> listar() {
		return votoRepository.findAll();
	}*/
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	@ResponseStatus(HttpStatus.CREATED)
	public VotoModel registrar(@PathVariable Long filmeId, @PathVariable Long usuarioId, @Valid @RequestBody VotoInput votoInput) {
		
		Voto votoRegistrado = votarService.registrar(filmeId, usuarioId, votoInput.getNota());
		
		return votoAssembler.toModel(votoRegistrado);
	}
	
}
