package com.myllamedeiros.projectmap.dto;

import java.io.Serializable;
import java.util.Base64;

import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.enums.Campus;

public class AuthorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nomeDeUsuario;
	private Campus campus;
	 
    private String imagem;	
	
	public AuthorDTO() {
	}

	public AuthorDTO(User user) {
		nomeDeUsuario = user.getNomeDeUsuario();
		campus = user.getCampus();
		
		if (user.getImagem() != null) {
	        this.imagem = Base64.getEncoder().encodeToString(user.getImagem());
	    }
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}
	
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}
}
