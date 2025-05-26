package com.myllamedeiros.projectmap.resources;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.dto.CommunityDTO;
import com.myllamedeiros.projectmap.dto.UserDTO;
import com.myllamedeiros.projectmap.enums.Campus;
import com.myllamedeiros.projectmap.enums.Tags;
import com.myllamedeiros.projectmap.services.CommunityService;
import com.myllamedeiros.projectmap.services.PostService;
import com.myllamedeiros.projectmap.util.ApresentarUsersECommunities;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/communities")
public class CommunityResource {

	@Autowired
	private CommunityService service;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ApresentarUsersECommunities apresentarUsersECommunities;
	
	@GetMapping
	public ResponseEntity<List<Community>> findAll(){
		List<Community> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{campus}/campus")
	public ResponseEntity<List<Community>> findByCampus(@PathVariable Campus campus){
		List<Community> list = service.findByCampus(campus);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Community> findByIdComplete(@PathVariable String id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		List<Post> listDoService = service.findPosts(id);
		List<Post> listDeRetorno = new LinkedList<>();
		for(Post p: listDoService) {
			System.out.print(p.getId());
			listDeRetorno.add(postService.findById(p.getId()));
		}
		System.out.println(listDeRetorno.size());
		return ResponseEntity.ok().body(listDeRetorno);
	}
	
	@GetMapping(value = "/{id}/dto")
	public ResponseEntity<CommunityDTO> findByIdDTO(@PathVariable String id){
		Community obj = service.findById(id);
		return ResponseEntity.ok().body(new CommunityDTO(obj));
	}
	
	@GetMapping(value = "/{nome}/name")
	public ResponseEntity<Community> findByNameDTO(@PathVariable String nome){
		Community obj = service.findByName(nome);
		System.out.println("Fui chamado");
		return ResponseEntity.ok().body(obj);
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
			@RequestParam("tags") String tags,
			@RequestParam("imagem") MultipartFile imagem){
		
		if(nome.isBlank() || descricao.isBlank() || campus == null || imagem == null || tags.isBlank()) {
			return ResponseEntity.badRequest().build(); 
		}
		
	    try {
	    	Set<Tags> tagSet = Arrays.stream(tags.split(","))
                    .map(String::toUpperCase)
                    .map(Tags::valueOf)
                    .collect(Collectors.toSet());
	    	
	    	Community obj = service.insert(new Community(nome, descricao, campus, tagSet), imagem);
	        
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
	
	@PatchMapping(value = "/{id}/imagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> updateImagem(
	        @PathVariable String id,
	        @RequestParam("imagem") MultipartFile imagem) {
	    
	    try {
	        service.updateImagem(id, imagem);
	        return ResponseEntity.noContent().build();
	    } catch (IOException e) {
	        return ResponseEntity.internalServerError().build();
	    }
	}
}
