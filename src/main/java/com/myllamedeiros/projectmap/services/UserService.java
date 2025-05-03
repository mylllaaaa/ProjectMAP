  package com.myllamedeiros.projectmap.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.repository.UserRepository;
import com.myllamedeiros.projectmap.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository rep;

	public List<User> findAll(){
		return rep.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User user, MultipartFile imagem) throws IOException {
		user.setImagem(imagem.getBytes());
		return rep.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
	public User update(User newObj, String id) {
		User inicialObj = rep.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		partialUpdate(inicialObj, newObj);
		return rep.save(inicialObj);   
	}
	
	private void partialUpdate(User inicialObj, User newObj) {
		
	    if (newObj.getNome() != null) {
			inicialObj.setNome(newObj.getNome());
	    }
	    if (newObj.getNomeDeUsuario() != null) {
			inicialObj.setNomeDeUsuario(newObj.getNomeDeUsuario());
	    }
	    if (newObj.getEmail() != null) {
			inicialObj.setEmail(newObj.getEmail());
	    }
	    
	    if (newObj.getCampus() != null) {
			inicialObj.setCampus(newObj.getCampus());
	    }
	    if (newObj.getCurso() != null) {
			inicialObj.setCurso(newObj.getCurso());
	    }
	    if (newObj.getDataNascimento() != null) {
			inicialObj.setDataNascimento(newObj.getDataNascimento());
	    }
	    
	    if (newObj.getSenha() != null) {
			inicialObj.setSenha(newObj.getSenha());
	    }
	    if (newObj.getDescricao() != null) {
			inicialObj.setDescricao(newObj.getDescricao());
	    }
	    if (newObj.getDenuncias() != null) {
			inicialObj.setDenuncias(newObj.getDenuncias()); 
	    }
	}
}
