package com.spring.artistas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.artistas.dto.ArtistaDTO;
import com.spring.artistas.entities.Artista;
import com.spring.artistas.repositories.ArtistaRepository;
import com.spring.artistas.services.exception.ResourceNotFoundException;

@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository repository;
	
	@Transactional(readOnly = true)
	public ArtistaDTO findById(Integer id) {
		Optional<Artista> obj = repository.findById(id);
		Artista entity = obj.orElseThrow(() -> new ResourceNotFoundException("Artista não encontrado -> " + id));
		return new ArtistaDTO(entity, entity.getAlbuns());
	}
}
