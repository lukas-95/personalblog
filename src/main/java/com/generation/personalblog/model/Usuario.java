package com.generation.personalblog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "o atributo NOME é obrigatorio")
    private String nome;

    @NotNull(message = "o atributo USUARIO é obrigatorio")
    @Email(message = "o atributo usuario vai receber um e-mail válido")
    private String usuario;

    @NotBlank(message = "o atributo SENHA é obrigatorio")
    @Size(min = 8, message = "A senha tem que ter no mínimo 8 caracteres")
    private String senha;

    @Size(max = 5000, message = "O link da foto não pode ser maior de 5.000 caracteres")
    private String foto;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<Postagem> postagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Postagem> getPostagem() {
        return postagem;
    }

    public void setPostagem(List<Postagem> postagem) {
        this.postagem = postagem;
    }


}
