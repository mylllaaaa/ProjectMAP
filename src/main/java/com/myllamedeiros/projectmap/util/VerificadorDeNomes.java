package com.myllamedeiros.projectmap.util;

import java.util.List;
import java.util.stream.Collectors;

import com.myllamedeiros.projectmap.domain.User;

public class VerificadorDeNomes {
	public static boolean verificarNomeDeUsuario(String nome, List<User> list) {
		List<String> listaDeNome = list.stream().map(x -> x.getNomeDeUsuario()).collect(Collectors.toList());
		if(listaDeNome.contains(nome)) {
			return false;
		}
		return true;
	}
}
