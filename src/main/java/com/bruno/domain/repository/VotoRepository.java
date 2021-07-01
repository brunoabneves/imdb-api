package com.bruno.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{

	List<Voto> findByFilmeId(Long id);
	Optional<Voto> findByUsuarioId(Long id);
	Optional<Voto> findByFilmeIdAndUsuarioId(Long filmeId, Long usuarioId);
	List<Voto> findByFilme(String nome);
	List<Voto> findAllByFilmeId(Long filmeId);

}
