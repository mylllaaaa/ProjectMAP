package com.myllamedeiros.projectmap.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myllamedeiros.projectmap.domain.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

	
}
