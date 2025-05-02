 package com.myllamedeiros.projectmap.dto;

import java.io.Serializable;

import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.enums.Campus;
import com.myllamedeiros.projectmap.enums.Curso;
import com.myllamedeiros.projectmap.util.VerificadorDeIdades;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomeDeUsuario;
	private Campus campus;
	private Curso curso;
	private String descricao;
	private Integer idade;
	
	public UserDTO() {
		
	}
	public UserDTO(User user) {
		super();
		nomeDeUsuario = user.getNomeDeUsuario();
		campus = user.getCampus();
		curso = user.getCurso();
		descricao = user.getDescricao();
		idade = VerificadorDeIdades.retornarIdade(user.getDataNascimento());
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
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
