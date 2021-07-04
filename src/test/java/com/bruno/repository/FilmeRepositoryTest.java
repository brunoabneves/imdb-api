package com.bruno.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bruno.domain.model.Filme;
import com.bruno.domain.repository.FilmeRepository;

@DataJpaTest
@DisplayName("Testes para o FilmeRepository")
class FilmeRepositoryTest {

	@Autowired
	private FilmeRepository filmeRepository;
	
	@Test
	@DisplayName("'save' cria um filme quando bem sucedido")
	void save_PersistirFilme_QuandoBemSucedido() {
		Filme filmeASerSalvo = criaFilme();
		Filme filmeSalvo = this.filmeRepository.save(filmeASerSalvo);
		
		Assertions.assertThat(filmeSalvo).isNotNull();
		Assertions.assertThat(filmeSalvo.getId()).isNotNull();
		Assertions.assertThat(filmeSalvo.getNome()).isEqualTo(filmeASerSalvo.getNome());
	}
	
	@Test
	@DisplayName("'Atualizar' atualiza um filme quando bem sucedido")
	void update_AtualizaFilme_QuandoBemSucedido() {
		Filme filmeASerSalvo = criaFilme();
		Filme filmeSalvo = this.filmeRepository.save(filmeASerSalvo);
		
		Assertions.assertThat(filmeSalvo).isNotNull();
		
		filmeSalvo.setNome("Interestelar");
		filmeSalvo.setDiretor("Christopher Nolan");
		filmeSalvo.setGenero("Ficção Científica");
		
		Filme filmeAtualizado = this.filmeRepository.save(filmeSalvo);
		
		Assertions.assertThat(filmeAtualizado).isNotNull();		
		Assertions.assertThat(filmeAtualizado.getId()).isNotNull();	
		Assertions.assertThat(filmeAtualizado.getNome()).isEqualTo(filmeSalvo.getNome());		
		Assertions.assertThat(filmeAtualizado.getDiretor()).isEqualTo(filmeSalvo.getDiretor());	
		Assertions.assertThat(filmeAtualizado.getGenero()).isEqualTo(filmeSalvo.getGenero());
	}
	
	@Test
	@DisplayName("'Find By Nome' retorna uma lista de filme quando bem sucedido")
	void findByNome_RetornaListaDeFilme_QuandoBemSucedido() {
		Filme filmeASerSalvo = criaFilme();
		
		Filme filmeSalvo = this.filmeRepository.save(filmeASerSalvo);
		
		String nome = filmeSalvo.getNome();
		
		List<Filme> filmes = this.filmeRepository.findByNome(nome);
		
		Assertions.assertThat(filmes).isNotNull();
		Assertions.assertThat(filmes).isNotEmpty();
		Assertions.assertThat(filmes).contains(filmeSalvo);
		Assertions.assertThat(filmes.get(0).getNome()).isEqualTo(filmeSalvo.getNome());

	}
	
	@Test
	@DisplayName("'Find By Nome Containing' retorna uma lista de filme quando bem sucedido")
	void findByNomeContainig_RetornaListaDeFilme_QuandoBemSucedido() {
		Filme filmeASerSalvo = criaFilme();
		
		Filme filmeSalvo = this.filmeRepository.save(filmeASerSalvo);
		
		List<Filme> filmes = this.filmeRepository.findByNomeContaining("Liga");
		
		Assertions.assertThat(filmes).isNotEmpty();
		Assertions.assertThat(filmes).isNotNull();
		Assertions.assertThat(filmes).contains(filmeSalvo);
		Assertions.assertThat(filmes.get(0).getNome()).isEqualTo(filmeSalvo.getNome());

	}
	
	@Test
	@DisplayName("'Find By Diretor Containing' retorna uma lista de filme quando bem sucedido")
	void findByDiretorContainig_RetornaListaDeFilme_QuandoBemSucedido() {
		Filme filmeASerSalvo = criaFilme();
		
		Filme filmeSalvo = this.filmeRepository.save(filmeASerSalvo);
		
		List<Filme> filmes = this.filmeRepository.findByDiretorContaining("Zack");
		
		Assertions.assertThat(filmes).isNotEmpty();
		Assertions.assertThat(filmes).isNotNull();
		Assertions.assertThat(filmes).contains(filmeSalvo);
		Assertions.assertThat(filmes.get(0).getDiretor()).isEqualTo(filmeSalvo.getDiretor());

	}
	
	@Test
	@DisplayName("'Find By Genero' retorna uma lista de filme quando bem sucedido")
	void findByGenero_RetornaListaDeFilme_QuandoBemSucedido() {
		Filme filmeASerSalvo = criaFilme();
		
		Filme filmeSalvo = this.filmeRepository.save(filmeASerSalvo);
		
		String genero = filmeSalvo.getGenero();
		
		List<Filme> filmes = this.filmeRepository.findByGenero(genero);
		
		Assertions.assertThat(filmes).isNotEmpty();
		Assertions.assertThat(filmes).isNotNull();
		Assertions.assertThat(filmes).contains(filmeSalvo);
		Assertions.assertThat(filmes.get(0).getGenero()).isEqualTo(filmeSalvo.getGenero());

	}
	
	@Test
	@DisplayName("'Find By Nome' retorna uma lista vazia quando um filme não é encontrado")
	void findByNome_RetornaListaVazia_QuandoFilmeNaoEncontrado() {
		
		List<Filme> filmes = this.filmeRepository.findByNome("ef43fs4");
		
		Assertions.assertThat(filmes).isEmpty();

	}
	
	@Test
	@DisplayName("'Find By Nome Containing' retorna uma lista vazia quando um filme não é encontrado")
	void findByNomeContaining_RetornaListaVazia_QuandoFilmeNaoEncontrado() {
		
		List<Filme> filmes = this.filmeRepository.findByNomeContaining("ef43fs4");
		
		Assertions.assertThat(filmes).isEmpty();

	}
	
	@Test
	@DisplayName("'Find By Diretor Containing' retorna uma lista vazia quando um filme não é encontrado")
	void findByDiretorContaining_RetornaListaVazia_QuandoFilmeNaoEncontrado() {
		
		List<Filme> filmes = this.filmeRepository.findByDiretorContaining("ef43fs4");
		
		Assertions.assertThat(filmes).isEmpty();

	}
	
	@Test
	@DisplayName("'Find By Genero' retorna uma lista vazia quando um filme não é encontrado")
	void findByGeneroContaining_RetornaListaVazia_QuandoFilmeNaoEncontrado() {
		
		List<Filme> filmes = this.filmeRepository.findByGenero("ef43fs4");
		
		Assertions.assertThat(filmes).isEmpty();

	}
	
	@Test
	@DisplayName("'Find By Id' retorna uma lista de filme quando bem sucedido")
	void findById_RetornaListaDeFilme_QuandoBemSucedido() {
		Filme filmeASerSalvo = criaFilme();
		
		Filme filmeSalvo = this.filmeRepository.save(filmeASerSalvo);
		
		Optional<Filme> filmes = this.filmeRepository.findById(filmeASerSalvo.getId());
		
		Assertions.assertThat(filmes).isNotEmpty();
		Assertions.assertThat(filmes).isNotNull();
		Assertions.assertThat(filmes).contains(filmeSalvo);

	}
	
	private Filme criaFilme() {
		Filme filme = new Filme();
		filme.setNome("Liga da Justiça");
		filme.setDiretor("Zack Snyder");
		filme.setGenero("Héroi");
		
		return filme;
	}
}
