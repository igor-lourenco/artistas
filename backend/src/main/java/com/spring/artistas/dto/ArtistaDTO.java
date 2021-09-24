package com.spring.artistas.dto;

import java.io.Serializable;

import com.spring.artistas.entities.Artista;

public class ArtistaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String nacionalidade;
	
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
}
