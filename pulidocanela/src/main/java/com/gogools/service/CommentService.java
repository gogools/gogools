package com.gogools.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogools.domain.Comment;
import com.gogools.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository cr;
	
	public Comment saveComment( Comment c) {
		
		return cr.save(c);
	}
}
