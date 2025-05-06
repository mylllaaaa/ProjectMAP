package com.myllamedeiros.projectmap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myllamedeiros.projectmap.domain.Community;
import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.services.CommunityService;
import com.myllamedeiros.projectmap.services.UserService;

@Component
public class AtualizadorDeUsersECommunity {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private CommunityService comService;
	
	public void atualizadorDeUsersECommunity(String matricula, String id) {
		User user = userService.findById(matricula);
		Community comm = comService.findById(id);
		
		user.getCommunitiesIds().add(id);
		comm.getUsersIds().add(matricula);
		
		userService.atualizadorDeCommunity(user);
		comService.atualizadorDeUsers(comm);
	}
}
