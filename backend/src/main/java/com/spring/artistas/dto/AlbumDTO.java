package com.spring.artistas.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spring.artistas.entities.Album;
import com.spring.artistas.entities.Artista;

public class AlbumDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Integer ano;
	
	private Set<ArtistaDTO> participantes = new HashSet<>();
	
	public AlbumDTO() {
		
	}

	public AlbumDTO(Integer id, String nome, Integer ano) {
		this.id = id;
		this.nome = nome;
		this.ano = ano;
	}
	
	public AlbumDTO(Album entity) {
		id = entity.getId();
		nome = entity.getNome();
		ano = entity.getAno();
	}
	
	public AlbumDTO(Album entity, List<Artista> participantes) {
		this(entity);
		participantes.forEach(part -> this.participantes.add(new ArtistaDTO(part)));
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

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Set<ArtistaDTO> getParticipantes() {
		return participantes;
	}
}
