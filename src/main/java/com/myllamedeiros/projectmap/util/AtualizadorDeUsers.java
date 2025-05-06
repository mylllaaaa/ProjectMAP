package com.myllamedeiros.projectmap.util;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myllamedeiros.projectmap.domain.Community;
import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.services.CommunityService;
import com.myllamedeiros.projectmap.services.UserService;

@Component
public class AtualizadorDeUsers {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private CommunityService comService;
	
	public void atualizarListaDeUsers(String matricula, String id) {
		User user = userService.findById(matricula);
		Community comm = comService.findById(id);
		
		//user.getCommunities().addAll(Arrays.asList(comm));
		comm.getUsers().addAll(Arrays.asList(user));
		
		comService.atualizadorDeUsers(comm);
	}
}
