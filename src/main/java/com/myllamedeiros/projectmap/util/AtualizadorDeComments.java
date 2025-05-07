package com.myllamedeiros.projectmap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myllamedeiros.projectmap.domain.Comment;
import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.services.PostService;
import com.myllamedeiros.projectmap.services.UserService;

@Component
public class AtualizadorDeComments {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private PostService postService;
	
	public void atualizarListaDeCommentsDoUser(String id, Comment comment) {
		User user = userService.findById(id);
		user.getComments().add(comment);
		userService.updateListaDeComments(user);
	}
	
	public void atualizarListaDeCommentsDoPost(String id, Comment comment) {
		Post post = postService.findById(id);
		post.getComments().add(comment);
		postService.updates(post);
	}
}
