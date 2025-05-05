package com.myllamedeiros.projectmap.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.dto.AuthorDTO;
import com.myllamedeiros.projectmap.dto.PostDTO;
import com.myllamedeiros.projectmap.services.PostService;
import com.myllamedeiros.projectmap.util.AtualizadorDePostagens;
import com.myllamedeiros.projectmap.util.CriadorDeUsuarioDTO;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@Autowired
	private CriadorDeUsuarioDTO criadorDeAuthorDTO;
	
	@Autowired
	private AtualizadorDePostagens atualizadorDePostagens;
	
	@GetMapping()
	public ResponseEntity<List<Post>> findAll(){
		List<Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/dto")
	public ResponseEntity<List<PostDTO>> findAllDTO(){
		List<Post> list = service.findAll();
		List<PostDTO> listDTO = list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}/complete")
 	public ResponseEntity<Post> findByIdComplete(@PathVariable String id) {
 	    return ResponseEntity.ok(service.findById(id));
 	}
	
	@GetMapping(value = "/{id}/dto")
 	public ResponseEntity<PostDTO> findByIdDTO(@PathVariable String id) {
 	    Post post = service.findById(id);
 	    return ResponseEntity.ok(new PostDTO(post));
 	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> savePost(
		@RequestParam("titulo") String postTitle,
		@RequestParam("descricao") String postDescription,
		@RequestParam("matricula") String matriculaDoAutor,
		@RequestParam(required = true) MultipartFile imagem) {
		
		if (imagem.isEmpty() || postTitle == null || postDescription == null || matriculaDoAutor == null) {
	        return ResponseEntity.badRequest().build(); 
	    }
	    try {
	    	AuthorDTO author = criadorDeAuthorDTO.retornaAuthorDTO(matriculaDoAutor);
	    	Post post = new Post(postTitle, postDescription, author);
	        post = service.savePost(post, imagem);
	        URI uri = ServletUriComponentsBuilder
	        	.fromCurrentRequest()
	            .path("/{id}")  
	            .buildAndExpand(post.getId())
	            .toUri();
	        atualizadorDePostagens.atualizarListaDePosts(matriculaDoAutor, post);
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

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody Post newPost, @PathVariable String id){
		newPost = service.update(newPost, id);
		return ResponseEntity.noContent().build();
	}
}
