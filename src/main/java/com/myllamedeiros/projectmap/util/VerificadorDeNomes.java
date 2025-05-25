package com.myllamedeiros.projectmap.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.services.UserService;

@Component
public class VerificadorDeNomes {

	@Autowired 
	private UserService userService;
	
	public boolean verificarNomeDeUsuario(String nome) {
		List<User> list = userService.findAll();
		List<String> listaDeNome = list.stream().map(x -> x.getNomeDeUsuario()).collect(Collectors.toList());
		if(listaDeNome.contains(nome)) {
			return false;
		}
		return true;
	}
}
