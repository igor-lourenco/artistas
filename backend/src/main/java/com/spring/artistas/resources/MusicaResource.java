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

import com.spring.artistas.dto.MusicaDTO;
import com.spring.artistas.services.MusicaService;

@RestController
@RequestMapping(value = "/musicas")
public class MusicaResource {
	
	@Autowired
	private MusicaService service;
	
	@GetMapping
	public ResponseEntity<Page<MusicaDTO>> findAllPaged(Pageable pageable) {
		Page<MusicaDTO> entity = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(entity);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MusicaDTO> findById(@PathVariable Integer id) {
		MusicaDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<MusicaDTO> insert(@RequestBody MusicaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(dto.getId()).toUri();  //mostra no cabeçalho da resposta o endereço da entidade criada
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<MusicaDTO> update(@PathVariable Integer id, @RequestBody MusicaDTO dto) {
		MusicaDTO entity = service.update(id, dto);	
		return ResponseEntity.ok().body(entity);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MusicaDTO> delete(@PathVariable Integer id) {
		 service.delete(id);	
		return ResponseEntity.noContent().build();
	}

}
