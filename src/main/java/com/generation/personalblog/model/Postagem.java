package com.generation.personalblog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="tb_postagens")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Esse atributo é de preenchimento obrigatório")
    @Size(min = 5, max = 100, message = "Este atributo tem que ter no minimo 5 caracteres e no maximo 100 caracteres")
    private String titulo;

    @NotBlank(message = "Esse atributo é de preenchimento obrigatório")
    @Size(min = 5, max = 1000, message = "Este atributo tem que ter no minimo 10 caracteres e no maximo 1000 caracteres")
    private String texto;

    @UpdateTimestamp
    private LocalDateTime data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
    
    


}
