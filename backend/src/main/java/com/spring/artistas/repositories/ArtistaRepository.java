package com.spring.artistas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.artistas.entities.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Integer>{

}
