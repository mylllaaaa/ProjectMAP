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
}
