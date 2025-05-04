package com.myllamedeiros.projectmap.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.myllamedeiros.projectmap.dto.AuthorDTO;

@Document(collection="post")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String titulo;
	private String descricao;
	private Integer denuncias; 
	private AuthorDTO autor;
	private Date data;
	
	@Field(targetType = FieldType.BINARY) 
    private byte[] imagem;	
	
	public Post() {
		
	}
	
	public Post(String titulo, String descricao, AuthorDTO autor) {
	   this.titulo = titulo;
	   this.descricao = descricao;
	   setAutor(autor);
	   denuncias = 0;
	   setData(new Date());
	}

	public String getId_post() {
		return id;
	}

	public void setId_post(String id_post) {
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

	public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Integer getDenuncias() {
		return denuncias;
	}

	public void setDenuncias(Integer denuncias) {
		this.denuncias = denuncias;
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
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}

	public AuthorDTO getAutor() {
		return autor;
	}

	public void setAutor(AuthorDTO autor) {
		this.autor = autor;
	}
	
}
