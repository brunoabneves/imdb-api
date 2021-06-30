package com.bruno.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{

	Optional<Voto> findByFilmeId(Long id);
	Optional<Voto> findByUsuarioId(Long id);
	Optional<Voto> findByFilmeIdAndUsuarioId(Long filmeId, Long usuarioId);

}
