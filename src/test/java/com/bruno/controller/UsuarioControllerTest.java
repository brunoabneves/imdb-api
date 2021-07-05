package com.bruno.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bruno.domain.model.Usuario;
import com.bruno.util.UsuarioCreator;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes para o UsuarioController")
public class UsuarioControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void listar_DeveRetornarSucesso_QuandoBuscarUsuario() throws Exception {
		
		mockMvc.perform(get("/usuarios"))
			.andExpect(status().isOk());
		
	}
	
	@Test
	public void salvar_DeveRetornarFilme_QuandoCriaFilme() throws Exception {
		Usuario usuario = UsuarioCreator.criaUsuarioValido();
		
		mockMvc.perform(post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(usuario)))
				.andExpect(status().isCreated());
	}
	
}
