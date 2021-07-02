package com.bruno.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.model.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long>{

	List<Ator> findByNomeContaining(String nome);

	List<Ator> findByFilmeId(Long filmeId);
	
}
