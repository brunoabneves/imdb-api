package com.bruno.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	//Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByUsername(String username);

	List<Usuario> findByAdministradorAndAtivo(boolean admin, boolean ativo);
}
