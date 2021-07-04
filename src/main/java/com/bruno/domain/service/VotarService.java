package com.bruno.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bruno.domain.model.Filme;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.model.Voto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VotarService {

	private CrudFilmeService crudFilmeService;
	private CrudUsuarioService crudUsuarioService;
	
	@Transactional
	public Voto registrar(Long filmeId, Long usuarioId, Long nota) {
		Filme filme = crudFilmeService.buscar(filmeId);
		Usuario usuario = crudUsuarioService.buscar(usuarioId);
		
		return filme.adicionarVoto(usuario, nota);
	}
	
}
