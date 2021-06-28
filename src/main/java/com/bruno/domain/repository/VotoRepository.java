package com.bruno.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{

	Optional<Voto> findByFilme(Long id);
	Optional<Voto> findByUsuario(Long id);

}
