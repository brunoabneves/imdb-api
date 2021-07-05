package com.bruno.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bruno.domain.model.Usuario;
import com.bruno.domain.repository.UsuarioRepository;
import com.bruno.util.UsuarioCreator;

@DataJpaTest
@DisplayName("Testes para o UsuarioRepository")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	@DisplayName("'save' cria um usuario quando bem sucedido")
	void save_PersistirUsuario_QuandoBemSucedido() {
		Usuario usuarioASerSalvo = UsuarioCreator.criaUsuarioASerSalvo();
		Usuario usuarioSalvo = this.usuarioRepository.save(usuarioASerSalvo);
		
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
		Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo(usuarioASerSalvo.getNome());
		Assertions.assertThat(usuarioSalvo.getUsername()).isEqualTo(usuarioASerSalvo.getUsername());
		Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo(usuarioASerSalvo.getSenha());
		Assertions.assertThat(usuarioSalvo.isAdministrador());
		Assertions.assertThat(usuarioSalvo.isAtivo());
	}
	
	@Test
	@DisplayName("'Atualizar' atualiza um usuario quando bem sucedido")
	void update_AtualizaUsuario_QuandoBemSucedido() {
		Usuario usuarioASerSalvo = UsuarioCreator.criaUsuarioASerSalvo();
		Usuario usuarioSalvo = this.usuarioRepository.save(usuarioASerSalvo);
		
		Assertions.assertThat(usuarioSalvo).isNotNull();
		
		usuarioSalvo.setNome("Túrin Turambar");
		usuarioSalvo.setUsername("ruinaDeGlaurung");
		usuarioSalvo.setSenha("turambar");
		usuarioSalvo.setAdministrador(false);
		usuarioSalvo.setAtivo(true);
		
		Usuario usuarioAtualizado = this.usuarioRepository.save(usuarioSalvo);
		
		Assertions.assertThat(usuarioAtualizado).isNotNull();	
		Assertions.assertThat(usuarioAtualizado.getId()).isNotNull();		
		Assertions.assertThat(usuarioAtualizado.getNome()).isEqualTo(usuarioSalvo.getNome());		
		Assertions.assertThat(usuarioAtualizado.getUsername()).isEqualTo(usuarioSalvo.getUsername());
		Assertions.assertThat(usuarioAtualizado.getSenha()).isEqualTo(usuarioSalvo.getSenha());		
		Assertions.assertThat(usuarioAtualizado.isAdministrador());
		Assertions.assertThat(usuarioAtualizado.isAtivo());
	}
	
	@Test
	@DisplayName("'delete By Id ' realiza a exclusão lógica de um usuario quando bem sucedido")
	void deleteById_ExclusaoLogica_QuandoBemSucedido() {
		Usuario usuarioASerSalvo = UsuarioCreator.criaUsuarioASerSalvo();
		
		Usuario usuarioSalvo = this.usuarioRepository.save(usuarioASerSalvo);
		
		this.usuarioRepository.deleteById(usuarioSalvo.getId());
		
		Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(usuarioSalvo.getId());
		
		Assertions.assertThat(usuarioOptional).isEmpty();
	}
	
	@Test
	@DisplayName("'exists By Id ' retorna true se usuário existe quando bem sucedido")
	void existsById_RetornaTrue_QuandoBemSucedido() {
		Usuario usuarioASerSalvo = UsuarioCreator.criaUsuarioASerSalvo();
		Usuario usuarioSalvo = this.usuarioRepository.save(usuarioASerSalvo);
		
		Long id = usuarioSalvo.getId();
		
		boolean usuarios = this.usuarioRepository.existsById(id);
		
		Assertions.assertThat(usuarios).isTrue();
	}
	
	@Test
	@DisplayName("'Find By Adiministrador And Ativo' retorna uma lista de usuario quando bem sucedido")
	void findByAdministradorAndAtivo_RetornaListaDeUsuario_QuandoBemSucedido() {
		Usuario usuarioASerSalvo = UsuarioCreator.criaUsuarioASerSalvo();
		
		Usuario usuarioSalvo = this.usuarioRepository.save(usuarioASerSalvo);
		
		boolean administrador = usuarioSalvo.isAdministrador();
		boolean ativo = usuarioSalvo.isAtivo();
		
		List<Usuario> usuarios = this.usuarioRepository.findByAdministradorAndAtivo(administrador, ativo);
		
		Assertions.assertThat(usuarios).isNotEmpty();
		Assertions.assertThat(usuarios).contains(usuarioSalvo);
		Assertions.assertThat(usuarios.get(0).isAdministrador()).isFalse();
		Assertions.assertThat(usuarios.get(0).isAtivo()).isTrue();
	}
	
	@Test
	@DisplayName("'Find By Username' retorna um usuário quando bem sucedido")
	void findByUsername_RetornaListaDeUsuario_QuandoBemSucedido() {
		Usuario usuarioASerSalvo = UsuarioCreator.criaUsuarioASerSalvo();
		
		Usuario usuarioSalvo = this.usuarioRepository.save(usuarioASerSalvo);
		
		String username = usuarioSalvo.getUsername();
		
		Optional<Usuario> usuarios = this.usuarioRepository.findByUsername(username);
		
		Assertions.assertThat(usuarios).isNotEmpty();
		Assertions.assertThat(usuarios).contains(usuarioSalvo);

	}
	
	@Test
	@DisplayName("'Find By Username' retorna uma lista vazia caso username procurado não exista")
	void findByUsername_RetornaListaVazia_QuandoUsernameNaoEncontrado() {
		
		Optional<Usuario> usuario = this.usuarioRepository.findByUsername("ef43fs4");
		
		Assertions.assertThat(usuario).isNotPresent();

	}
	
	@Test
	@DisplayName("'exists By Id ' retorna false quando id do usuario não existe")
	void existsById_RetornaFalse_QuandoIdUsuarioNaoExiste() {
		
		boolean usuarios = this.usuarioRepository.existsById(3L);
		
		Assertions.assertThat(usuarios).isFalse();

	}
	
	@Test
	@DisplayName("'Find By Adiministrador And Ativo' retorna uma lista vazia quando um usuário não é encontrado")
	void findByAdministradorAndAtivo_RetornaListaVazia_QuandoUsuarioNaoEncontrado() {
		
		List<Usuario> usuarios = this.usuarioRepository.findByAdministradorAndAtivo(true, false);
		
		Assertions.assertThat(usuarios).isEmpty();

	}
}
