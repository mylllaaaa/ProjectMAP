package com.myllamedeiros.projectmap.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myllamedeiros.projectmap.domain.Comment;
import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.dto.CommentDTO;
import com.myllamedeiros.projectmap.services.CommentService;
import com.myllamedeiros.projectmap.util.AtualizadorDeComments;
import com.myllamedeiros.projectmap.util.CriadorDeUsuarioDTO;

@RestController
@RequestMapping(value="/comments")
public class CommentResource {

	@Autowired
	private CommentService service;
	
	@Autowired
	private CriadorDeUsuarioDTO criadorDeAuthorDTO;
	
	@Autowired
	private AtualizadorDeComments atualizadorDeComments;
	
	@GetMapping()
	public ResponseEntity<List<Comment>> findAll(){
		List<Comment> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/dto")
	public ResponseEntity<List<CommentDTO>> findAllDTO(){
		List<Comment> list = service.findAll();
		List<CommentDTO> listDTO = list.stream().map(x -> new CommentDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
 	public ResponseEntity<Comment> findByIdComplete(@PathVariable String id) {
 	    Comment Comment = service.findById(id); 
 	    return ResponseEntity.ok(Comment);
 	}
	
	@GetMapping(value = "/{id}/dto")
 	public ResponseEntity<CommentDTO> findByIdDTO(@PathVariable String id) {
 	    Comment Comment = service.findById(id);
 	    CommentDTO CommentDTO = new CommentDTO(Comment);

 	    return ResponseEntity.ok(CommentDTO);
 	}
	
	@PostMapping
	public ResponseEntity<Void> saveComment(
		@RequestParam("descricao") String commentDescription,
		@RequestParam("matricula") String matriculaDoAutor,
		@RequestParam("id_post") String idDoPost){
		
		if (commentDescription == null || matriculaDoAutor == null || idDoPost == null) {
	        return ResponseEntity.badRequest().build(); 
	    }
	  
	    Comment comment = new Comment(commentDescription, criadorDeAuthorDTO.retornaAuthorDTO(matriculaDoAutor), idDoPost);
	    comment = service.saveComment(comment); //tlz seja melhor mudar isso de função
	    URI uri = ServletUriComponentsBuilder
	        	.fromCurrentRequest()
	            .path("/{id}")  
	            .buildAndExpand(comment.getId())
	            .toUri();
	    
	    atualizadorDeComments.atualizarListaDeCommentsDoPost(idDoPost, comment);
	    atualizadorDeComments.atualizarListaDeCommentsDoUser(matriculaDoAutor, comment);
	    return ResponseEntity.created(uri).build(); 
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody Comment newComment, @PathVariable String id){
		newComment = service.update(newComment, id);
		return ResponseEntity.noContent().build();
	}
	

	@PatchMapping("/{id}/curtidas")
	public ResponseEntity<Void> curtidas(@PathVariable String id) {
		Comment comment = service.findById(id);
		comment.setCurtidas();
		service.updates(comment);
		return ResponseEntity.noContent().build();
	}
}
