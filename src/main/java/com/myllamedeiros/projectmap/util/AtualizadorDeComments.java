package com.myllamedeiros.projectmap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myllamedeiros.projectmap.domain.Comment;
import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.services.PostService;

@Component
public class AtualizadorDeComments {
	
	@Autowired 
	private PostService postService;
	
	public void atualizarListaDeComments(String id, Comment comment) {
		Post post = postService.findById(id);
		post.getComments().add(comment);
		postService.updateListaDeComments(post);
	}
}
