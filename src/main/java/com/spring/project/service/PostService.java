package com.spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.entity.Post;
import com.spring.project.repository.PostRepo;

@Service
public class PostService {
	private PostRepo postRepo;
	
	@Autowired
	public PostService(PostRepo postRepo) {
		super();
		this.postRepo = postRepo;
	}
	
	public Post savePost(Post post) {
		return postRepo.save(post);
	}
	
	public List<Post> allPost(){
		return postRepo.findAll();
	}
	
	public Post findOne(long id) {
		return postRepo.getOne(id);
	}
	
	public void deletePost(Post post) {
		postRepo.delete(post);
	}
}
