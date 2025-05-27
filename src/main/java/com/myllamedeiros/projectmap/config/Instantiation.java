package com.myllamedeiros.projectmap.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.myllamedeiros.projectmap.domain.Comment;
import com.myllamedeiros.projectmap.domain.Community;
import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.domain.User;
import com.myllamedeiros.projectmap.dto.AuthorDTO;
import com.myllamedeiros.projectmap.enums.Campus;
import com.myllamedeiros.projectmap.enums.Curso;
import com.myllamedeiros.projectmap.enums.Tags;
import com.myllamedeiros.projectmap.repository.CommentRepository;
import com.myllamedeiros.projectmap.repository.CommunityRepository;
import com.myllamedeiros.projectmap.repository.PostRepository;
import com.myllamedeiros.projectmap.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommunityRepository comRepository;
	
	@Autowired
	private CommentRepository commentRepsitory;
	
	@Override
	public void run(String... args) throws Exception {
		
		/*userRepository.deleteAll();
		comRepository.deleteAll();
		postRepository.deleteAll();
		commentRepsitory.deleteAll();
		
		SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
		//User myllena = new User("498406791", "Myllena de Sousa Medeiros", "Mylla","mylla@gmail.com", Campus.CAMPUSVII, Curso.COMPUTACAO, sdf.parse("12/05/2005"), "anellym", "Namorada de André");
		User pax = new User("15158254954", "Pax au Augustus", "TheSonOfTheRepublic","pax@gmail.com", Campus.CAMPUSVII, Curso.COMPUTACAO, sdf.parse("12/05/2005"), "123", "Son of Darrow and Mustang");
		User electra = new User("02126518748", "Electra au Barca-Julii", "LittleDemon","electra@gmail.com", Campus.CAMPUSVII, Curso.COMPUTACAO, sdf.parse("12/05/2005"), "456", "Son of Sevro and Victra");
		User lyria = new User("0158489852315", "Lyria of Lagalos", "Piggy","lyria@gmail.com", Campus.CAMPUSVII, Curso.COMPUTACAO, sdf.parse("12/05/2005"), "789", "A Red");
		userRepository.saveAll(Arrays.asList(pax, electra, lyria));
		
		Community com1 = new Community("ASOAIF", "Melhor saga de fantasia do mundo", Campus.CAMPUSVII, new LinkedHashSet<>());
		Community com2 = new Community("RED RISING", "Melhor saga de Sci-fi do mundo", Campus.CAMPUSVII, new LinkedHashSet<>());
		com1.getTags().add(Tags.FICCAO_CIENTIFICA);
		com1.getTags().add( Tags.GUERRA);
		com1.getTags().add(Tags.AVENTURA);
		comRepository.saveAll(Arrays.asList(com1, com2));
		
		Post post = new Post("Indo ao espaço", "UHULLLLLLL", new AuthorDTO(pax), com2.getId());
		postRepository.saveAll(Arrays.asList(post));
		
		Comment comment = new Comment("É isso aeeeeee", new AuthorDTO(electra), post.getId());
		commentRepsitory.saveAll(Arrays.asList(comment));
		
		userRepository.saveAll(Arrays.asList(pax, electra, lyria));
		comRepository.saveAll(Arrays.asList(com1, com2));
		postRepository.saveAll(Arrays.asList(post));
		commentRepsitory.saveAll(Arrays.asList(comment));
		*/
	}
}
