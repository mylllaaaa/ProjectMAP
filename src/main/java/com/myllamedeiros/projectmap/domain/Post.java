package com.myllamedeiros.projectmap.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myllamedeiros.projectmap.dto.AuthorDTO;

@Document(collection="post")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String titulo;
	private String descricao;
	private Integer curtidas;
	private Integer denuncias; 
	private AuthorDTO author;
	private Date data;
	private String id_comunidade;
	
	@Field(targetType = FieldType.BINARY) 
    private byte[] imagem;	
	
	@DBRef(lazy = true)
	private Set<Comment> comments =  new LinkedHashSet<>();
	
	public Post() {
		
	}
	
	public Post(String titulo, String descricao, AuthorDTO author, String id_comunidade) {
	   this.titulo = titulo;
	   this.descricao = descricao;
	   curtidas = 0;
	   denuncias = 0;
	   this.author = author;
	   data = new Date();
	   this.id_comunidade = id_comunidade;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id_post) {
		this.id = id_post;
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

	public void setCurtidas() {
		curtidas += 1;
	}

	public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
	
	public Integer getDenuncias() {
		return denuncias;
	}

	public void setDenuncias() {
		denuncias += 1;
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
	
	public Set<Comment> getComments() {
		return comments;
	}
	
	public String getId_comunidade() {
		return id_comunidade;
	}

	public void setId_comunidade(String id_comunidade) {
		this.id_comunidade = id_comunidade;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String imagemFilePath() {
		String imagemUrl = ServletUriComponentsBuilder
     	        .fromCurrentContextPath()
     	        .path("/posts/")
     	        .path(id)
     	        .path("/imagem")
     	        .toUriString();
		return imagemUrl;
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
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}
}
