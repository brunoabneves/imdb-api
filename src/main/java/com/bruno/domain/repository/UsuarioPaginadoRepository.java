package com.bruno.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bruno.domain.model.Usuario;

@Repository
public interface UsuarioPaginadoRepository extends PagingAndSortingRepository<Usuario, Long>{
	Usuario findByUsername(String username );
}
