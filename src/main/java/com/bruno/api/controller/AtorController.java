package com.bruno.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.domain.model.Ator;
import com.bruno.domain.repository.AtorRepository;
import com.bruno.domain.service.CrudAtorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/filmes/{filmeId}/atores")
public class AtorController {

	private CrudAtorService crudAtorService;
	private AtorRepository atorRepository;
	
	@GetMapping
	public List<Ator> listar() {
		return atorRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ator cadastrar (@PathVariable Long filmeId, @RequestBody @Valid Ator ator) {
		return crudAtorService.registrar(filmeId, ator.getNome());
	}
	
}
