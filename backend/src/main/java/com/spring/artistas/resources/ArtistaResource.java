package com.spring.artistas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
