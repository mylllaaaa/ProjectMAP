package com.myllamedeiros.projectmap.services;

import java.io.IOException;
import java.util.Date;
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
	
	public Post savePost(Post post, MultipartFile imagem) throws IOException {
        post.setImagem(imagem.getBytes());
        return rep.save(post);
    }
	
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
	public Post update(Post newObj, String id) {
		Post inicialObj = rep.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		partialUpdate(inicialObj, newObj);
		return rep.save(inicialObj);   
	}

	private void partialUpdate(Post inicialObj, Post newObj) {
	    if (newObj.getTitulo() != null) {
	    	inicialObj.setTitulo(newObj.getTitulo());
	    }
	    if (newObj.getDescricao() != null) {
	    	inicialObj.setDescricao(newObj.getDescricao());
	    }
	    if (newObj.getImagem() != null) {
	    	inicialObj.setImagem(newObj.getImagem());
	    } //não pode alterar a imagem, mas deixa aqui pra garantir que ela não vai receber null
	    
	    inicialObj.setData(new Date());
	}
	
	public void updates(Post post) {
		rep.save(post);
	}

}
