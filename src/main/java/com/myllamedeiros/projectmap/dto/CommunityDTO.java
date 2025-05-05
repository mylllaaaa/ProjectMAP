 package com.myllamedeiros.projectmap.dto;

import java.io.Serializable;

import com.myllamedeiros.projectmap.domain.Community;
import com.myllamedeiros.projectmap.enums.Campus;

public class CommunityDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descricao;
	private Campus campus;
	
	public CommunityDTO() {
		
	}
	
	public CommunityDTO(Community community) {
		super();
		nome = community.getNome();
		campus = community.getCampus();
		descricao = community.getDescricao();
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Campus getCampus() {
		return campus;
	}
	
	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
