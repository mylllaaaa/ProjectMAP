package com.myllamedeiros.projectmap.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myllamedeiros.projectmap.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	
}
