package com.generation.personalblog.controller;

import com.generation.personalblog.model.Usuario;
import com.generation.personalblog.repository.UsuarioRepository;
import com.generation.personalblog.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start(){
        usuarioRepository.deleteAll();

        usuarioService.cadastrarUsuario(
                new Usuario (0L,"Root", "root@root.com","rootroot", " "));
    }

    @Test
    @DisplayName("Cadastrar um Usuário")
    public void deveCriarUmUsuario(){

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario
                (0L, "Lucas", "lucas@lucas.com", "12345678", ""));

        ResponseEntity <Usuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());

    }

    @Test
    @DisplayName("Não deve duplicar usuario")
    public void naoDeveDuplicarUsuario (){

        usuarioService.cadastrarUsuario( new Usuario
                (0L,"Rapunzel", "rapz@rapz.com","12345678", " "));

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario
                (0L,"Rapunzel", "rapz@rapz.com","12345678", " "));

        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);

        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());

    }

    @Test
    @DisplayName("Atualizar um Usuário")
    public void deveAtualizarUsuario(){

        Optional<Usuario> usuarioCadastrado = usuarioService
                .cadastrarUsuario(new Usuario
                        (0L,"Rapunzel", "rapz@rapz.com","12345678", " "));

        Usuario usuarioUpdate = new Usuario(usuarioCadastrado.get().getId(),
                "Cinderela e Rapunzel", "cinder@disney.com","12345678", " " );

        HttpEntity<Usuario> corpoRequisicao= new HttpEntity<Usuario>(usuarioUpdate);

        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .withBasicAuth("root@root.com","rootroot")
                .exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuario.class);
        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Mostrar todos os Usuários")
    public void deveMostrarTodosUsuarios(){

        usuarioService.cadastrarUsuario( new Usuario
                (0L,"Malevola", "malev@disney.com","12345678", " "));
        usuarioService.cadastrarUsuario( new Usuario
                (0L,"Ariel", "ariel@disney.com","87654321", " "));

        ResponseEntity<String> resposta = testRestTemplate
                .withBasicAuth("root@root.com","rootroot")
                .exchange("/usuarios/all", HttpMethod.GET,null, String.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }




}
