package com.bruno.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioPaginadoRepository;

@DataJpaTest
@DisplayName("Testes para o UsuarioPaginadoRepository")
public class UsuarioPaginadoRepositoryTest {
	
	@Autowired
	private UsuarioPaginadoRepository usuarioPaginadoRepository;
	
	@Test
	@DisplayName("'Find By Username' retorna um usuário quando bem sucedido")
	void findByUsername_RetornaListaDeUsuario_QuandoBemSucedido() {
		Usuario usuarioASerSalvo = criaUsuario();
		
		Usuario usuarioSalvo = this.usuarioPaginadoRepository.save(usuarioASerSalvo);
		
		String username = usuarioSalvo.getUsername();
		
		Usuario usuarios = this.usuarioPaginadoRepository.findByUsername(username);
		
		Assertions.assertThat(usuarios).isNotNull();
		Assertions.assertThat(usuarios).isEqualTo(usuarioSalvo);

	}
	
	@Test
	@DisplayName("'Find By Username' retorna null caso username procurado não exista")
	void findByNome_RetornaListaVazia_QuandoUsuarioNaoEncontrado() {
		
		Usuario usuario = this.usuarioPaginadoRepository.findByUsername("ef43fs4");
		
		Assertions.assertThat(usuario).isNull();;

	}
	
	private Usuario criaUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Aragorn");
		usuario.setUsername("passolargo");
		usuario.setSenha("forFrodo");
		usuario.setAdministrador(false);
		usuario.setAtivo(true);
		
		return usuario;
	}

}
