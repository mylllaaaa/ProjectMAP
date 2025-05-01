package com.myllamedeiros.projectmap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository rep;

	public List<User> findAll(){
		return rep.findAll();
	}
}
