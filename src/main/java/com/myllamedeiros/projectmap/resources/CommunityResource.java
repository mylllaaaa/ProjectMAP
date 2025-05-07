package com.myllamedeiros.projectmap.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myllamedeiros.projectmap.domain.Community;
import com.myllamedeiros.projectmap.dto.CommunityDTO;
import com.myllamedeiros.projectmap.dto.UserDTO;
import com.myllamedeiros.projectmap.enums.Campus;
import com.myllamedeiros.projectmap.services.CommunityService;
import com.myllamedeiros.projectmap.util.ApresentarUsersECommunities;



@RestController
@RequestMapping(value="/communities")
public class CommunityResource {

	@Autowired
	private CommunityService service;
	
	@Autowired
	private ApresentarUsersECommunities apresentarUsersECommunities;
	
	@GetMapping
	public ResponseEntity<List<Community>> findAll(){
		List<Community> list = service.findAll();
		//List<CommunityDTO> listDTO = list.stream().map(x -> new CommunityDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Community> findByIdComplete(@PathVariable String id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@GetMapping(value = "/{id}/dto")
	public ResponseEntity<CommunityDTO> findByIdDTO(@PathVariable String id){
		Community obj = service.findById(id);
		return ResponseEntity.ok().body(new CommunityDTO(obj));
	}
	
	@GetMapping(value = "/{id}/users")
	public ResponseEntity<Set<String>> findCommunitiesIds(@PathVariable String id) {
		Community obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getUsersIds());
	}
	
	@GetMapping(value = "/{id}/users/dto")
	public ResponseEntity<List<UserDTO>> findUsersIdsDto(@PathVariable String id) {
		List<UserDTO> list = apresentarUsersECommunities.retornandoUsers(id);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> insert(
			@RequestParam("nome") String nome, 
			@RequestParam("descricao") String descricao,
			@RequestParam("campus") Campus campus,
			@RequestParam("imagem") MultipartFile imagem){
		
		if(nome.isBlank() || descricao.isBlank() || campus == null || imagem == null) {
			return ResponseEntity.badRequest().build(); 
		}
		
	    try {
	    	Community obj = service.insert(new Community(nome, descricao, campus), imagem);
	        
	        URI uri = ServletUriComponentsBuilder
	        	.fromCurrentRequest()
	            .path("/{id}")  
	            .buildAndExpand(obj.getId())
	            .toUri();
	        
	        return ResponseEntity.created(uri).build(); 
	    } catch (IOException e) {
	        System.out.print("Erro ao salvar Community: " + e.getMessage());
	        return ResponseEntity.internalServerError().build(); 
	    } 
	}
	
	@GetMapping("/{id}/imagem")
	public ResponseEntity<byte[]> getImagem(@PathVariable String id) {
	    Community Community = service.findById(id);
	    
	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_JPEG) 
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"imagem.jpg\"")
	            .body(Community.getImagem());
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Community newCommunity, @PathVariable String id){
		newCommunity = service.update(newCommunity, id);
		return ResponseEntity.noContent().build();
	}

}
