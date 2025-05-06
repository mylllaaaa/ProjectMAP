  package com.myllamedeiros.projectmap.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myllamedeiros.projectmap.domain.Community;
import com.myllamedeiros.projectmap.repository.CommunityRepository;
import com.myllamedeiros.projectmap.services.exception.ObjectNotFoundException;

@Service
public class CommunityService {
	
	@Autowired
	private CommunityRepository rep;

	public List<Community> findAll(){
		return rep.findAll();
	}
	
	public Community findById(String id) {
		Optional<Community> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
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
}
