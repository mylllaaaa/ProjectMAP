package com.myllamedeiros.projectmap.domain;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;

public class Post implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id_post;
	private String titulo;
	private String descricao;
	private String imagemUrl;
	
	public Post(String titulo, String descricao, String imagemUrl) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.imagemUrl = imagemUrl;
	}

	public String getId_post() {
		return id_post;
	}

	public void setId_post(String id_post) {
		this.id_post = id_post;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_post);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id_post, other.id_post);
	}
	
}
