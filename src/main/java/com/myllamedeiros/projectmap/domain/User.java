package com.myllamedeiros.projectmap.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.myllamedeiros.projectmap.enums.Campus;
import com.myllamedeiros.projectmap.enums.Curso;

@Document(collection="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String matricula;
	private String nome;
	private String email;
	private Campus campus;
	private Curso curso;
	private Date dataNascimento;
	private int denuncias;
	private String senha;
	
	public User(String matricula, String nome, String email, Campus campus, Curso curso, Date dataNascimento, String senha) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.campus = campus;
		this.curso = curso;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.denuncias = 0;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getDenuncias() {
		return denuncias;
	}

	public void setDenuncias(int denuncias) {
		this.denuncias = denuncias;
	}
	
	public String getSenha() {
		return senha;
	}

	public void alterarSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(matricula, other.matricula);
	}
	
}
