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

import com.spring.artistas.dto.AlbumDTO;
import com.spring.artistas.dto.ArtistaDTO;
import com.spring.artistas.entities.Album;
import com.spring.artistas.entities.Artista;
import com.spring.artistas.repositories.AlbumRepository;
import com.spring.artistas.repositories.ArtistaRepository;
import com.spring.artistas.services.exception.DatabaseException;
import com.spring.artistas.services.exception.ResourceNotFoundException;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository repository;

	@Autowired
	private ArtistaRepository artistaRepository;

	@Transactional(readOnly = true)
	public AlbumDTO findById(Integer id) {
		Optional<Album> obj = repository.findById(id);
		Album entity = obj.orElseThrow(() -> new ResourceNotFoundException("Album não encontrado -> " + id));
		return new AlbumDTO(entity, entity.getParticipantes());
	}

	@Transactional(readOnly = true)
	public Page<AlbumDTO> findAllPaged(Pageable pageable) {
		Page<Album> entity = repository.findAll(pageable);
		return entity.map(x -> new AlbumDTO(x, x.getParticipantes()));
	}

	@Transactional()
	public AlbumDTO insert(AlbumDTO dto) {
		Album entity = new Album();
		copyDtoEntity(dto, entity);
		entity = repository.save(entity);
		return new AlbumDTO(entity);
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
	public AlbumDTO update(Integer id, AlbumDTO dto) {
		try {
			Album entity = repository.getOne(id);
			copyDtoEntity(dto, entity);
			entity = repository.save(entity);
			return new AlbumDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}

	private void copyDtoEntity(AlbumDTO dto, Album entity) {
		entity.setNome(dto.getNome());
		entity.setAno(dto.getAno());

		entity.getParticipantes().clear();
		for (ArtistaDTO artistaDto : dto.getParticipantes()) {
			Artista artista = artistaRepository.getOne(artistaDto.getId());
			entity.getParticipantes().add(artista);
		}
	}
}
