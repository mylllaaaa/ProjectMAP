package com.myllamedeiros.projectmap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myllamedeiros.projectmap.domain.Community;
import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.services.CommunityService;
import com.myllamedeiros.projectmap.services.UserService;

@Component
public class AtualizadorDeNotificacoes {
	
	@Autowired
	CommunityService communityService;
	
	@Autowired
	UserService userService;
	
	public void notificacaoDeNovoPost(String idComunidade) {
		Community comunidade = communityService.findById(idComunidade);
		String notificacao = "Novo post na comunidade " + comunidade.getNome();
		
		for(String userId: comunidade.getUsersIds()) {
			User user = userService.findById(userId);
			user.getNotificacoes().add(notificacao);
			userService.updates(user);
		}
	}
}
