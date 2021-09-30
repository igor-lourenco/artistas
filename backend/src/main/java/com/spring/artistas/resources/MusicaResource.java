package com.spring.artistas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.artistas.dto.MusicaDTO;
import com.spring.artistas.services.MusicaService;

@RestController
@RequestMapping(value = "/musicas")
public class MusicaResource {
	
	@Autowired
	private MusicaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MusicaDTO> findById(@PathVariable Integer id) {	
		MusicaDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
