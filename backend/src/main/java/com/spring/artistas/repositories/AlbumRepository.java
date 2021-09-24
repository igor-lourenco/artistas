package com.spring.artistas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.artistas.entities.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer>{

}
