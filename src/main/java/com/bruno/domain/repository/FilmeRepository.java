package com.bruno.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{
	List<Filme> findByNome(String nome);
}
