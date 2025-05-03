package com.myllamedeiros.projectmap.dto;

import java.io.Serializable;

import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.enums.Campus;

public class AuthorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomeDeUsuario;
	private Campus campus;
	
	public AuthorDTO() {
	}

	public AuthorDTO(User user) {
		nomeDeUsuario = user.getNomeDeUsuario();
		campus = user.getCampus();
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}
}
