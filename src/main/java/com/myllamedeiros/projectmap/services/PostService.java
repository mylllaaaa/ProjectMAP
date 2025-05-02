package com.myllamedeiros.projectmap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
}
