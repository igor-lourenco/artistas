package com.spring.artistas.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.spring.artistas.entities.Artista;
import com.spring.artistas.entities.Musica;

public class MusicaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Integer duracao;
	
	private Set<ArtistaDTO> autores = new HashSet<>();
	
	public MusicaDTO() {
		
	}

	public MusicaDTO(Integer id, String nome, Integer duracao) {
		this.id = id;
		this.nome = nome;
		this.duracao = duracao;
	}
	
	public MusicaDTO(Musica entity) {
		id = entity.getId();
		nome = entity.getNome();
		duracao = entity.getDuracao();
	}
	
	public MusicaDTO(Musica entity, List<Artista> autores) {
		this(entity);
		autores.forEach(aut -> this.autores.add(new ArtistaDTO(aut)));
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

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Set<ArtistaDTO> getAutores() {
		return autores;
	}
}
