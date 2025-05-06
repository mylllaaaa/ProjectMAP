package com.myllamedeiros.projectmap.dto;

import java.io.Serializable;
import java.util.Date;

import com.myllamedeiros.projectmap.domain.Comment;

public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descricao;
	private Integer curtidas;
	private AuthorDTO author;
	private Date data;
	
	public CommentDTO() {
		
	}
	
	public CommentDTO(Comment comment) {
		setDescricao(comment.getDescricao());
		setCurtidas(comment.getCurtidas());
		setAuthor(comment.getAuthor());
		setData(comment.getData());
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getCurtidas() {
		return curtidas;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public Date getData() {
		return data;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCurtidas(Integer curtidas) {
		this.curtidas = curtidas;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
