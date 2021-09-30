package com.spring.artistas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.artistas.dto.MusicaDTO;
import com.spring.artistas.entities.Musica;
import com.spring.artistas.repositories.MusicaRepository;
import com.spring.artistas.services.exception.ResourceNotFoundException;

@Service
public class MusicaService {
	
	@Autowired
	private MusicaRepository repository;
	
	@Transactional(readOnly = true)
	public MusicaDTO findById(Integer id) {
		Optional<Musica> obj = repository.findById(id);
		Musica entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado !!" + id));
		return new MusicaDTO(entity);
	}

}
