package com.spring.artistas.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spring.artistas.entities.Album;
import com.spring.artistas.entities.Artista;
import com.spring.artistas.entities.Musica;

public class ArtistaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String nacionalidade;
	
	private Set<AlbumDTO> albuns = new HashSet<>();	
	private Set<MusicaDTO> musicasComoAutor = new HashSet<>();
	
	public ArtistaDTO() {
		
	}

	public ArtistaDTO(Integer id, String nome, String nacionalidade) {
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
	}
	
	public ArtistaDTO(Artista entity) {
		id = entity.getId();
		nome = entity.getNome();
		nacionalidade = entity.getNacionalidade();
	}
	
	public ArtistaDTO(Artista entity, List<Album> albuns, List<Musica> musicasComoAutor) {
		this(entity);
		albuns.forEach(alb -> this.albuns.add(new AlbumDTO(alb)));
		musicasComoAutor.forEach(musicas -> this.musicasComoAutor.add(new MusicaDTO(musicas)));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Set<AlbumDTO> getAlbuns() {
		return albuns;
	}

	public Set<MusicaDTO> getMusicasComoAutor() {
		return musicasComoAutor;
	}
}
