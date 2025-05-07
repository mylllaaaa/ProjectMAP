package com.myllamedeiros.projectmap.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.myllamedeiros.projectmap.enums.Campus;
import com.myllamedeiros.projectmap.enums.Tags;

public class Community implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String nome;
	private String descricao;
	private Campus campus;
	private Date dataCriacao;
	private Set<Tags> tags;
	
	private Set<String> usersIds = new LinkedHashSet<>();
	
	@DBRef(lazy = true)
	private Set<Post> posts = new LinkedHashSet<>();
		
	@Field(targetType = FieldType.BINARY) 
    private byte[] imagem;	

	public Community() {
		
	}

	public Community(String nome, String descricao, Campus campus, Set<Tags> tags) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.tags = tags;
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
	
	public Set<String> getUsersIds() {
		return usersIds;
	}

	public Set<Post> getPosts() {
		return posts;
	}
	
	public Set<Tags> getTags() {
		return tags;
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
