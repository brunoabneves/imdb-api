package com.bruno.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.domain.model.Voto;
import com.bruno.domain.repository.VotoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/votos")
public class VotoController {

	private VotoRepository votoRepository;
	
	//método apenas de teste
	@GetMapping
	public List<Voto> listar() {
		return votoRepository.findAll();
	}
}
