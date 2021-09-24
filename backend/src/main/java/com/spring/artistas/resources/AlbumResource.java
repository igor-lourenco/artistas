package com.spring.artistas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.artistas.dto.AlbumDTO;
import com.spring.artistas.services.AlbumService;

@RestController
@RequestMapping(value = "/albuns")
public class AlbumResource {

	@Autowired
	private AlbumService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AlbumDTO> findById(@PathVariable Integer id) {
		AlbumDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
