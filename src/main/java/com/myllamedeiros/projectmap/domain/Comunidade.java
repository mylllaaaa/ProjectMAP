package com.myllamedeiros.projectmap.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

public class Comunidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String nome;
	private String descricao;
	private List<User> usuarios;
	private List<Post> posts;
	
	@Field(targetType = FieldType.BINARY) 
    private byte[] imagem;	

}
