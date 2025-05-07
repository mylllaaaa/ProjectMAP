package com.myllamedeiros.projectmap.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myllamedeiros.projectmap.domain.Comment;
import com.myllamedeiros.projectmap.repository.CommentRepository;
import com.myllamedeiros.projectmap.services.exception.ObjectNotFoundException;

@Service
public class CommentService {

	@Autowired
	private CommentRepository rep;
	
	public List<Comment> findAll(){
		return rep.findAll();
	}
	
	public Comment findById(String id) {
		Optional<Comment> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Comment saveComment(Comment Comment)  {
        return rep.save(Comment);
    }
	
	public void delete(String id) {
		findById(id);
		rep.deleteById(id);
	}
	
	public Comment update(Comment newObj, String id) {
		Comment inicialObj = rep.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		partialUpdate(inicialObj, newObj);
		return rep.save(inicialObj);   
	}

	private void partialUpdate(Comment inicialObj, Comment newObj) {
	    if (newObj.getDescricao() != null) {
	    	inicialObj.setDescricao(newObj.getDescricao());
	    } 
	    inicialObj.setData(new Date());
	}
	
	public void updates(Comment comment) {
		rep.save(comment);
	}
}
