package com.generation.personalblog.controller;

import com.generation.personalblog.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.personalblog.model.Postagem;
import com.generation.personalblog.repository.PostagemRepository;


import java.util.List;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*" )
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping
    public ResponseEntity <List<Postagem>> getAll(){
        return ResponseEntity.ok(postagemRepository.findAll());
    }
	
	


}