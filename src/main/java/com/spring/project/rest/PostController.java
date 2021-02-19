package com.spring.project.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.entity.Post;
import com.spring.project.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PostMapping("/sendPost")
	public ResponseEntity<Post> sentPost(@RequestBody Post post){
		return ResponseEntity.ok().body(postService.savePost(post));
	}
	
	@GetMapping("/showallPost")
	public ResponseEntity<List<Post>> showPosts(){
		return ResponseEntity.ok().body(postService.allPost());
	}
	
	@GetMapping("/getPost/{id}")
	public ResponseEntity<Post> getPost(@PathVariable long id){
		return ResponseEntity.ok().body(postService.findOne(id));
	}
	
	@PutMapping("/updatePost/{id}")
	public ResponseEntity<Post> editPost(@PathVariable long id, @RequestBody Post post){
		Post Userpost = postService.findOne(id);
		if(Userpost != null) {
			Post p=new Post();
			p.setId(Userpost.getId());
			p.setTitle(post.getTitle());
			p.setComments(post.getComments());
			
			return ResponseEntity.ok().body(postService.savePost(p));
		}
		else {
			return ResponseEntity.ok().body(new Post());
		}
		
	}
}
