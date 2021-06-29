package com.bruno.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{
	Optional<Filme> findByNomeContaining(String nome);
	Optional<Filme> findByNome(String nome);
	Optional<Filme> findByGenero(String genero);
	Optional<Filme> findByDiretorContaining(String diretor);
	//List<Filme> findByAtorContaining(String ator);
	
}
