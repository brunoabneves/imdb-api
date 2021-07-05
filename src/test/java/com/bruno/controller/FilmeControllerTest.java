package com.bruno.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.bruno.domain.model.Filme;
import com.bruno.util.FilmeCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes para o FilmeController")
public class FilmeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
//	@MockBean
//	private CrudFilmeService CrudfilmeService;
	
	@Test
	public void buscar_DeveRetornarSucesso_QuandoBuscarFilme() throws Exception {
		
		mockMvc.perform(get("/filmes"))
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void salvar_DeveRetornarFilme_QuandoCriaFilme() throws Exception {
		Filme filme = FilmeCreator.criaFilmeValido();
		
		mockMvc.perform(post("/filmes")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(filme)))
				.andExpect(status().isCreated());
	}
}
