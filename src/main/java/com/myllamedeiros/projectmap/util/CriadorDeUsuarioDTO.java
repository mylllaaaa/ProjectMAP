package com.myllamedeiros.projectmap.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.dto.AuthorDTO;
import com.myllamedeiros.projectmap.services.UserService;

@Component
public class CriadorDeUsuarioDTO {
	
	@Autowired 
	private UserService userService;
	
	public AuthorDTO retornaAuthorDTO(String matricula) {
		User user = userService.findById(matricula);
		return new AuthorDTO(user);
	}
}
