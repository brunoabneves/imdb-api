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

import com.bruno.api.assembler.AtorAssembler;
import com.bruno.api.model.AtorModel;
import com.bruno.domain.model.Ator;
import com.bruno.domain.repository.AtorRepository;
import com.bruno.domain.service.CrudAtorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/atores")
public class AtorController {

	private CrudAtorService crudAtorService;
	private AtorRepository atorRepository;
	private AtorAssembler atorAssembler;
	
	@GetMapping
	public List<AtorModel> listar() {
		return atorAssembler.toCollectionModel(atorRepository.findAll());
	}
	
	@PostMapping("/{filmeId}")
	@ResponseStatus(HttpStatus.CREATED)
	public AtorModel cadastrar (@PathVariable Long filmeId, @RequestBody @Valid Ator ator) {
		return atorAssembler.toModel(crudAtorService.registrar(filmeId, ator.getNome()));
	}
	
}
