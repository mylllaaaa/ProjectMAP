package com.myllamedeiros.projectmap.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.myllamedeiros.projectmap.enums.Campus;

public class Community implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String nome;
	private String descricao;
	private Campus campus;
	private Date dataCriacao;
	
	@DBRef(lazy = true)
	private List<User> users =  new LinkedList<>();
	
	@DBRef(lazy = true)
	private List<Post> posts =  new LinkedList<>();
	
	@Field(targetType = FieldType.BINARY) 
    private byte[] imagem;	

	public Community() {
		
	}

	public Community(String nome, String descricao, Campus campus) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.setCampus(campus);
		setDataCriacao(new Date());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsuarios(List<User> users) {
		this.users = users;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Community other = (Community) obj;
		return Objects.equals(id, other.id);
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	
}
