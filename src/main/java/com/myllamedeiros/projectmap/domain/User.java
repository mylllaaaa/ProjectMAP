package com.myllamedeiros.projectmap.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.myllamedeiros.projectmap.enums.Campus;
import com.myllamedeiros.projectmap.enums.Curso;

@Document(collection="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String matricula;
	private String nome;
	private String nomeDeUsuario;
	private String email;
	private Campus campus;
	private Curso curso;
	private Date dataNascimento;
	private Integer denuncias;
	private String senha;
	private String descricao;
	private Date dataCriacao;
	
	@Field(targetType = FieldType.BINARY) 
    private byte[] imagem;	
	
	@DBRef(lazy = true)
	private List<Post> posts =  new LinkedList<>();

	public User(String matricula, String nome, String nomeDeUsuario, String email, Campus campus, Curso curso, Date dataNascimento, String senha, String descricao) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.nomeDeUsuario = nomeDeUsuario;
		this.email = email;
		this.campus = campus;
		this.curso = curso;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.descricao =  descricao;
		this.denuncias = 0;
		setDataCriacao(new Date());
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

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
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

	public Integer getDenuncias() {
		return denuncias;
	}

	public void setDenuncias(Integer denuncias) {
		this.denuncias = denuncias;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	public List<Post> getPosts() {
		return posts;
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
}
