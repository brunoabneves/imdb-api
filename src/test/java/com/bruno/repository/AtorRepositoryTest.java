package com.bruno.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bruno.domain.model.Ator;
import com.bruno.domain.model.Filme;
import com.bruno.domain.repository.AtorRepository;
import com.bruno.domain.repository.FilmeRepository;

@DataJpaTest
@DisplayName("Testes para o AtorRepository")
public class AtorRepositoryTest {

	@Autowired
	private AtorRepository atorRespository;
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Test
	@DisplayName("'delete By Id ' retorna uma lista vazia quando bem sucedido")
	void deleteById_RetornaListaVazia_QuandoBemSucedido() {
		Ator ator = criaAtor();
		
		Filme filmeSalvo = this.filmeRepository.save(ator.getFilme());
		
		this.atorRespository.deleteById(ator.getId());
		
		Optional<Ator> atorOptional = this.atorRespository.findById(filmeSalvo.getAtor().get(0).getId());
		
		Assertions.assertThat(atorOptional).isEmpty();
	}
	
	@Test
	@DisplayName("'Find By Id' retorna uma lista de atores quando bem sucedido")
	void findById_RetornaListaDeAtores_QuandoBemSucedido() {
		
		Ator ator = criaAtor();
		
		Filme filmeSalvo = this.filmeRepository.save(ator.getFilme());
		
		Optional<Ator> atores = this.atorRespository.findById(ator.getId());
		
		Assertions.assertThat(atores).isNotEmpty();
		Assertions.assertThat(atores).isNotNull();
		Assertions.assertThat(atores).contains(filmeSalvo.getAtor().get(0));

	}
	
	@Test
	@DisplayName("'Find By Nome Containing' retorna uma lista de ator quando bem sucedido")
	void findByNomeContainig_RetornaListaDeAtor_QuandoBemSucedido() {
		Ator ator = criaAtor();
		
		Filme filmeSalvo = this.filmeRepository.save(ator.getFilme());
		
		List<Ator> atores = this.atorRespository.findByNomeContaining("Mortense");
		
		Assertions.assertThat(atores).isNotEmpty();
		Assertions.assertThat(atores).isNotNull();
		Assertions.assertThat(atores).contains(filmeSalvo.getAtor().get(0));
		Assertions.assertThat(atores.get(0).getNome()).isEqualTo(filmeSalvo.getAtor().get(0).getNome());

	}
	
	@Test
	@DisplayName("'Find By Id' retorna uma lista vazia quando id do ator não existe")
	void findById_RetornaListaVazia_QuandoIdAtorNaoExiste() {
		
		Optional<Ator> atores = this.atorRespository.findById(45L);
		
		Assertions.assertThat(atores).isEmpty();

	}
	
	private Ator criaAtor() {
		Ator ator = new Ator();
		ator.setNome("Vigo Mortense");
		ator.setFilme(criaFilme());
		
		ator.getFilme().getAtor().add(ator);
		
		return ator;
	}
	
	private Filme criaFilme() {
		
		Filme filme = new Filme();

		filme.setNome("Liga da Justiça");
		filme.setDiretor("Zack Snyder");
		filme.setGenero("Héroi");
		
		return filme;
	}
}
