package com.spring.artistas.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.artistas.dto.AlbumDTO;
import com.spring.artistas.dto.ArtistaDTO;
import com.spring.artistas.dto.MusicaDTO;
import com.spring.artistas.entities.Album;
import com.spring.artistas.entities.Artista;
import com.spring.artistas.entities.Musica;
import com.spring.artistas.repositories.AlbumRepository;
import com.spring.artistas.repositories.ArtistaRepository;
import com.spring.artistas.repositories.MusicaRepository;
import com.spring.artistas.services.exception.ResourceNotFoundException;

@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository repository;

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private MusicaRepository musicaRepository;

	@Transactional(readOnly = true)
	public Page<ArtistaDTO> findAllPaged(Pageable pageable) {
		Page<Artista> entity = repository.findAll(pageable);
		return entity.map(x -> new ArtistaDTO(x, x.getAlbuns(), x.getMusicasComoAutor()));
	}

	@Transactional(readOnly = true)
	public ArtistaDTO findById(Integer id) {
		Optional<Artista> obj = repository.findById(id);
		Artista entity = obj.orElseThrow(() -> new ResourceNotFoundException("Artista não encontrado -> " + id));
		return new ArtistaDTO(entity, entity.getAlbuns(), entity.getMusicasComoAutor());
	}

	@Transactional()
	public ArtistaDTO insert(ArtistaDTO dto) {
		Artista entity = new Artista();
		copyDtoEntity(dto, entity);
		entity = repository.save(entity);
		return new ArtistaDTO(entity);
	}

	@Transactional()
	public ArtistaDTO update(Integer id, ArtistaDTO dto) {
		try {
			Artista entity = repository.getOne(id);
			copyDtoEntity(dto, entity);
			entity = repository.save(entity);
			return new ArtistaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}

	private void copyDtoEntity(ArtistaDTO dto, Artista entity) {
		entity.setNome(dto.getNome());
		entity.setNacionalidade(dto.getNacionalidade());

		entity.getAlbuns().clear();
		for (AlbumDTO albumDto : dto.getAlbuns()) {
			Album album = albumRepository.getOne(albumDto.getId());
			entity.getAlbuns().add(album);
		}

		for (MusicaDTO musicaDto : dto.getMusicasComoAutor()) {
			Musica musica = musicaRepository.getOne(musicaDto.getId());
			entity.getMusicasComoAutor().add(musica);
		}
	}
}
