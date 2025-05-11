package com.myllamedeiros.projectmap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myllamedeiros.projectmap.domain.Community;
import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.services.CommunityService;
import com.myllamedeiros.projectmap.services.UserService;

@Component
public class AtualizadorDePostagens {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private CommunityService commService;
	
	public void atualizarListaDePostsDoUser(String matricula, Post post) {
		User user = userService.findById(matricula);
		user.getPosts().add(post);
		userService.updates(user);
	}
	
	public void atualizarListaDePostsDaComunidade(String id, Post post) {
		Community comm = commService.findById(id);
		comm.getPosts().add(post);
		commService.updateListaDePosts(comm);
	}
}
