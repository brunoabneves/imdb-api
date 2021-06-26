package com.bruno.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.domain.model.Usuario;

public interface AdministradorRepository extends JpaRepository<Usuario, Long>{

}
