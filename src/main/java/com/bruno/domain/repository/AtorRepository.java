package com.bruno.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.domain.model.Ator;

public interface AtorRepository extends JpaRepository<Ator, Long>{

	List<Ator> findByNomeContaining(String nome);

}
