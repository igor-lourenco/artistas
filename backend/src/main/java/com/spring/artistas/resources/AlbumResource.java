package com.spring.artistas.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.artistas.dto.AlbumDTO;
import com.spring.artistas.services.AlbumService;

@RestController
@RequestMapping(value = "/albuns")
public class AlbumResource {

	@Autowired
	private AlbumService service;
	
	@GetMapping
	public ResponseEntity<Page<AlbumDTO>> findAllPaged(Pageable pageable) {
		Page<AlbumDTO> entity = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(entity);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AlbumDTO> findById(@PathVariable Integer id) {
		AlbumDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<AlbumDTO> insert(@RequestBody AlbumDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(dto.getId()).toUri();  //mostra no cabeçalho da resposta o endereço da entidade criada
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AlbumDTO> update(@PathVariable Integer id, @RequestBody AlbumDTO dto) {
		AlbumDTO entity = service.update(id, dto);	
		return ResponseEntity.ok().body(entity);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AlbumDTO> delete(@PathVariable Integer id) {
		 service.delete(id);	
		return ResponseEntity.noContent().build();
	}
}
