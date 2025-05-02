package com.myllamedeiros.projectmap.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myllamedeiros.projectmap.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	
}
