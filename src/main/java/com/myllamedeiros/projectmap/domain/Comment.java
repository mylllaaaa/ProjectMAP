package com.myllamedeiros.projectmap.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.myllamedeiros.projectmap.dto.AuthorDTO;

@Document(collection="comments")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String id_post;
	private String descricao;
	private Integer curtidas;
	private Integer denuncias; 
	private AuthorDTO author;
	private Date data;
	
	public Comment() {
		
	}

	public Comment(String descricao, AuthorDTO author, String id_post) {
		this.descricao = descricao;
		this.author = author;
		this.id_post = id_post;
		setCurtidas(0);
		setDenuncias(0);
		setData(new Date());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getDenuncias() {
		return denuncias;
	}

	public void setDenuncias(Integer denuncias) {
		this.denuncias = denuncias;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		Comment other = (Comment) obj;
		return Objects.equals(id, other.id);
	}

	public String getId_post() {
		return id_post;
	}

	public void setId_post(String id_post) {
		this.id_post = id_post;
	}
	
}
