  package com.myllamedeiros.projectmap.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public User insert(User user) {
		return rep.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
	public User update(User newObj) {
		User inicialObj = rep.findById(newObj.getMatricula()).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		updateData(inicialObj, newObj);
		return rep.save(newObj);   
	}

	private void updateData(User inicialObj, User newObj) {
		inicialObj.setNome(newObj.getNome());
		inicialObj.setNomeDeUsuario(newObj.getNomeDeUsuario());
		inicialObj.setEmail(newObj.getEmail());
		inicialObj.setCampus(newObj.getCampus());
		inicialObj.setCurso(newObj.getCurso());
		inicialObj.setDataNascimento(newObj.getDataNascimento());
		inicialObj.setSenha(newObj.getSenha());
		inicialObj.setDescricao(newObj.getDescricao());
		inicialObj.setDenuncias(newObj.getDenuncias()); 
	}
}
