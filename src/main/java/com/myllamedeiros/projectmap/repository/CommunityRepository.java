package com.myllamedeiros.projectmap.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myllamedeiros.projectmap.domain.Community;

@Repository
public interface CommunityRepository extends MongoRepository<Community, String> {

	
}
