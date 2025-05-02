package com.myllamedeiros.projectmap.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.enums.Campus;
import com.myllamedeiros.projectmap.enums.Curso;
import com.myllamedeiros.projectmap.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
		User myllena = new User("498406791", "Myllena de Sousa Medeiros", "mylla@gmail.com", Campus.CAMPUSVII, Curso.COMPUTACAO, sdf.parse("12/05/2005"), "anellym", "Namorada de André");
		
		userRepository.saveAll(Arrays.asList(myllena));
	}

}
