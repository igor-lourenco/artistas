package com.spring.artistas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.artistas.dto.AlbumDTO;
import com.spring.artistas.entities.Album;
import com.spring.artistas.repositories.AlbumRepository;
import com.spring.artistas.services.exception.ResourceNotFoundException;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository repository;
	
	@Transactional(readOnly = true)
	public AlbumDTO findById(Integer id) {
		Optional<Album> obj = repository.findById(id);
		Album entity = obj.orElseThrow(() -> new ResourceNotFoundException("Album não encontrado -> " + id));
		return new AlbumDTO(entity, entity.getParticipantes());
	}
}
