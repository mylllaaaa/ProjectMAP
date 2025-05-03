package com.myllamedeiros.projectmap.resources;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Post>> findAll(){
		List<Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
 	public ResponseEntity<Map<String, Object>> findById(@PathVariable String id) {
 	    Post post = service.findById(id);
 
 	    String imagemUrl = ServletUriComponentsBuilder
 	        .fromCurrentContextPath()
 	        .path("/posts/")
 	        .path(id)
 	        .path("/imagem")
 	        .toUriString();
 
 	    Map<String, Object> response = new LinkedHashMap<>();
 	    response.put("id", post.getId_post());
 	    response.put("titulo", post.getTitulo());
 	    response.put("descricao", post.getDescricao());
 	    response.put("imagemUrl", imagemUrl);
 
 	    return ResponseEntity.ok(response);
 	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> savePost(
	    @RequestParam(required = true) String titulo,
	    @RequestParam(required = true) String descricao,
	    @RequestParam(required = true) MultipartFile imagem
	) {

		if (imagem.isEmpty() || titulo.isBlank() || descricao.isBlank()) {
	        return ResponseEntity.badRequest().build(); // HTTP 400
	    }

	    try {
	        Post obj = service.savePost(titulo, descricao, imagem);
	        URI uri = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")  
	            .buildAndExpand(obj.getId_post())
	            .toUri();
	        
	        return ResponseEntity.created(uri).build(); 
	    } catch (IOException e) {
	        System.out.print("Erro ao salvar post: " + e.getMessage());
	        return ResponseEntity.internalServerError().build(); 
	    }
	}
	
	@GetMapping("/{id}/imagem")
	public ResponseEntity<byte[]> getImagem(@PathVariable String id) {
	    Post post = service.findById(id);
	    
	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_JPEG) 
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"imagem.jpg\"")
	            .body(post.getImagem());

	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Post newPost, @PathVariable String id){
		newPost = service.update(newPost, id);
		return ResponseEntity.noContent().build();
	}
}
