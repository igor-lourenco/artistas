package com.spring.artistas.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.artistas.dto.ArtistaDTO;
import com.spring.artistas.dto.MusicaDTO;
import com.spring.artistas.entities.Artista;
import com.spring.artistas.entities.Musica;
import com.spring.artistas.repositories.ArtistaRepository;
import com.spring.artistas.repositories.MusicaRepository;
import com.spring.artistas.services.exception.DatabaseException;
import com.spring.artistas.services.exception.ResourceNotFoundException;

@Service
public class MusicaService {
	
	@Autowired
	private MusicaRepository repository;
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Transactional(readOnly = true)
	public MusicaDTO findById(Integer id) {
		Optional<Musica> obj = repository.findById(id);
		Musica entity = obj.orElseThrow(() -> new ResourceNotFoundException("Musica não encontrado -> " + id));
		return new MusicaDTO(entity, entity.getAutores());
	}

	@Transactional(readOnly = true)
	public Page<MusicaDTO> findAllPaged(Pageable pageable) {
		Page<Musica> entity = repository.findAll(pageable);
		return entity.map(x -> new MusicaDTO(x, x.getAutores()));
	}

	@Transactional()
	public MusicaDTO insert(MusicaDTO dto) {
		Musica entity = new Musica();
		copyDtoEntity(dto, entity);
		entity = repository.save(entity);
		return new MusicaDTO(entity);
	}
	
	public void  delete(Integer id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("id não existe " + id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade no banco");
		}
	}
	
	@Transactional()
	public MusicaDTO update(Integer id, MusicaDTO dto) {
		try {
			Musica entity = repository.getOne(id);
			copyDtoEntity(dto, entity);
			entity = repository.save(entity);
			return new MusicaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}

	private void copyDtoEntity(MusicaDTO dto, Musica entity) {
		entity.setNome(dto.getNome());
		entity.setDuracao(dto.getDuracao());

		entity.getAutores().clear();
		for (ArtistaDTO artistaDto : dto.getAutores()) {
			Artista artista = artistaRepository.getOne(artistaDto.getId());
			entity.getAutores().add(artista);
		}
	}

}
