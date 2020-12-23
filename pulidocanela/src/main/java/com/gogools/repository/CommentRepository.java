package com.gogools.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gogools.domain.Comment;


public interface CommentRepository extends MongoRepository<Comment, String> {

	
}
