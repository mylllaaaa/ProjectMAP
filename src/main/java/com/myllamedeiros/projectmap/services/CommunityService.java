  package com.myllamedeiros.projectmap.services;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myllamedeiros.projectmap.domain.Community;
import com.myllamedeiros.projectmap.domain.Post;
import com.myllamedeiros.projectmap.enums.Campus;
import com.myllamedeiros.projectmap.repository.CommunityRepository;
import com.myllamedeiros.projectmap.services.exception.ObjectNotFoundException;

@Service
public class CommunityService {
	
	@Autowired
	private CommunityRepository rep;
	

	public List<Community> findAll(){
		return rep.findAll();
	}
	
	public List<Community> findByCampus(Campus campus){
		List<Community> listCampus = new LinkedList<>();
		List<Community> listGlobal = rep.findAll();
		for(Community c: listGlobal) {
			if(c.getCampus().equals(campus)) {
				listCampus.add(c);
			}
		}
		return listCampus;
	}
	
	public List<Post> findPosts(String idComunidade){
		Community comunidade = null;
		List<Community> list = rep.findAll();
		for(Community c: list) {
			if(c.getId().equals(idComunidade)) {
				comunidade = c;
			}
		}
		if(comunidade == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		Set<Post> setPosts = comunidade.getPosts();
		List<Post> listPosts = new LinkedList<>();
		for(Post p: setPosts) {
			listPosts.add(p);
		}
		return listPosts;
	}
	
	public Community findById(String id) {
		Optional<Community> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Community findByName(String name) {
		List<Community> list = rep.findAll();
		for(Community c: list) {
			if(c.getNome().equals(name)) {
				return c;
			}
		}
		throw new ObjectNotFoundException("Objeto não encontrado");
	}
	
	public Community insert(Community Comunity, MultipartFile imagem) throws IOException {
		Comunity.setImagem(imagem.getBytes());
		return rep.insert(Comunity);
	}
		
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
	public Community update(Community newObj, String id) {
		Community inicialObj = rep.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		partialUpdate(inicialObj, newObj);
		return rep.save(inicialObj);   
	}
	
	private void partialUpdate(Community inicialObj, Community newObj) {	
	    if (newObj.getNome() != null) {
			inicialObj.setNome(newObj.getNome());
	    }
	   inicialObj.setDataCriacao(new Date());
	}
	
	public void updateListaDePosts(Community community) {
		rep.save(community);
	}
	
	public void atualizadorDeUsers(Community community) {
		rep.save(community);
	}

	public void updateImagem(String id, MultipartFile imagem) throws IOException {
	    Community community = findById(id);
	    community.setImagem(imagem.getBytes());
	    rep.save(community);
	}

}
