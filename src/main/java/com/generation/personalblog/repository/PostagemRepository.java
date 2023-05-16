package com.generation.personalblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.personalblog.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
