package com.bruno.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioPaginadoRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioPaginadoRepository usuarioPaginadoRepository;

    @Autowired
    public CustomUserDetailsService (UsuarioPaginadoRepository usuarioPaginadoRepository) {
        this.usuarioPaginadoRepository = usuarioPaginadoRepository;
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        Usuario usuario = Optional.ofNullable(usuarioPaginadoRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

        return new org.springframework.security.core.userdetails.User
                (usuario.getUsername(), usuario.getSenha(), usuario.isAdministrador() ? authorityListAdmin : authorityListUser);
    }

}

