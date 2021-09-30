package com.spring.artistas.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.artistas.dto.ArtistaDTO;
import com.spring.artistas.services.ArtistaService;

@RestController
@RequestMapping(value = "/artistas")
public class ArtistaResource {

	@Autowired
	private ArtistaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ArtistaDTO> findById(@PathVariable Integer id) {
		ArtistaDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<ArtistaDTO> insert(@RequestBody ArtistaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(dto.getId()).toUri();  //mostra no cabeçalho da resposta o endereço da entidade criada
		return ResponseEntity.created(uri).body(dto);
	}
}
