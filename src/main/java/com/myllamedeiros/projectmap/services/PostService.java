package com.myllamedeiros.projectmap.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.repository.PostRepository;
import com.myllamedeiros.projectmap.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository rep;
	
	public List<Post> findAll(){
		return rep.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Post savePost(String titulo, String descricao, MultipartFile imagem) throws IOException {
        Post post = new Post();
        post.setTitulo(titulo);
        post.setDescricao(descricao);
		post.setImagem(imagem.getBytes());
        return rep.save(post);
    }
	
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
}
