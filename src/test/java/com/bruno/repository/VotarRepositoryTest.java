package com.bruno.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bruno.domain.model.Filme;
import com.bruno.domain.model.Usuario;
import com.bruno.domain.model.Voto;
import com.bruno.domain.repository.FilmeRepository;
import com.bruno.domain.repository.UsuarioRepository;
import com.bruno.domain.repository.VotoRepository;

@DataJpaTest
@DisplayName("Testes para o VotarRepository")
public class VotarRepositoryTest {

	@Autowired
	private VotoRepository votoRepository;
	@Autowired
	private FilmeRepository filmeRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	@DisplayName("'Find By Filme Id' retorna uma lista de votos de um filme quando bem sucedido")
	void FindByFilmeId_RetornaListaDeVotos_QuandoBemSucedido() {
		
		Voto voto = criaVoto();
		
		Filme filmeSalvo = this.filmeRepository.save(voto.getFilme());
		this.usuarioRepository.save(voto.getUsuario()); 
		
		List<Voto> votos = this.votoRepository.findByFilmeId(filmeSalvo.getId());
		
		Assertions.assertThat(votos).isNotEmpty();
		Assertions.assertThat(votos).isNotNull();
		Assertions.assertThat(votos).contains(filmeSalvo.getVotos().get(0));
	}
	
	@Test
	@DisplayName("'Find By Filme Id' retorna uma lista vazia quando um voto não é encontrado")
	void fFindByFilmeId_RetornaListaVazia_QuandoVotoNaoEncontrado() {
		
		List<Voto> votos = this.votoRepository.findByFilmeId(65L);
		
		Assertions.assertThat(votos).isEmpty();

	}
	
	private Voto criaVoto() {
		Voto voto = new Voto();
		voto.setNota(4L);
		voto.setDataVoto(OffsetDateTime.now());
		voto.setFilme(criaFilme());
		voto.setUsuario(criaUsuario());
		
		voto.getFilme().getVotos().add(voto);
		
		return voto;
	}
	
	private Filme criaFilme() {
		
		Filme filme = new Filme();

		filme.setNome("Liga da Justiça");
		filme.setDiretor("Zack Snyder");
		filme.setGenero("Héroi");
		
		return filme;
	}
	
	private Usuario criaUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Anakin Skywalker");
		usuario.setUsername("darthVader");
		usuario.setSenha("executeOrder66");
		usuario.setAdministrador(false);
		usuario.setAtivo(true);
		
		return usuario;
	}
}
