package com.myllamedeiros.projectmap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.services.UserService;

@Component
public class AtualizadorDePostagens {
	
	@Autowired 
	private UserService userService;
	
	public void atualizarListaDePosts(String matricula, Post post) {
		User user = userService.findById(matricula);
		user.getPosts().add(post);
		userService.updateListaDePosts(user);
	}
}
