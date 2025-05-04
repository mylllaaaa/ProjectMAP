package com.myllamedeiros.projectmap.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.myllamedeiros.projectmap.domain.Post;

public class PostDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String descricao;
	private Integer curtidas;
	private Date data;
	private AuthorDTO author;
	private String imagemUrl;
	
	public PostDTO() {
	}

	public PostDTO(Post post) {
		titulo = post.getTitulo();
		descricao = post.getDescricao();
		curtidas = post.getCurtidas();
		data = post.getData();
		author = post.getAuthor();
		imagemUrl = post.imagemFilePath();
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

	public Integer getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(Integer curtidas) {
		this.curtidas = curtidas;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public String getFilePath() {
		return imagemUrl;
	}

	public void setFilePath(String filePath) {
		this.imagemUrl = filePath;
	}
}
