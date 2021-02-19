package com.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.entity.Comments;
import com.spring.project.repository.CommentsRepo;

@Service
public class CommentService {
	private CommentsRepo commentsRepo;
	@Autowired
	public CommentService(CommentsRepo commentsRepo) {
		super();
		this.commentsRepo = commentsRepo;
	}
	
	public Comments saveComments(Comments comments) {
		comments.getPost().getComments();
		return commentsRepo.save(comments);				
	}
	
	public List<Comments> showComments(){
		return commentsRepo.findAll();
	}
	
	public Comments findComment(long id) {
		return commentsRepo.getOne(id);
	}
	
	public void deleteComment(Comments comment) {
		commentsRepo.delete(comment);
	}
}
