package com.spring.artistas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.artistas.entities.Musica;

public interface MusicaRepository extends JpaRepository<Musica, Integer>{

}
