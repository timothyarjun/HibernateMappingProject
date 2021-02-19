package com.spring.project.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.entity.Comments;
import com.spring.project.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentsController {
	private CommentService commentService;
	
	@Autowired
	public CommentsController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@PostMapping("/registerComment")
	public ResponseEntity<Comments> saveComment(@RequestBody Comments comments){
		return ResponseEntity.ok().body(commentService.saveComments(comments));
	}
	
	@GetMapping("/allComments")
	public ResponseEntity<List<Comments>> commentsList(){
		return ResponseEntity.ok().body(commentService.showComments());
	}
	
	@GetMapping("/getComment/{id}")
	public ResponseEntity<Comments> getComments(@PathVariable long id){
		return ResponseEntity.ok().body(commentService.findComment(id));
	}
	
	@DeleteMapping("/deleteComment/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable long id){
		Comments comment = commentService.findComment(id);
		if(comment != null) {
			commentService.deleteComment(comment);
			return ResponseEntity.ok().body("Comment Removed ...");
		}
		else {
			return ResponseEntity.ok().body("Comment is not there");
		}
			
	}
	
	@PutMapping("/editComment/{id}")
	public ResponseEntity<Comments> editComment(@PathVariable long id, @RequestBody Comments comments){
		Comments comment = commentService.findComment(id);
		if(comment != null) {
			Comments cms=new Comments();
			cms.setId(comment.getId());
			cms.setContext(comments.getContext());
			cms.setDate(comments.getDate());
			cms.setPost(comments.getPost());
			
			return ResponseEntity.ok().body(commentService.saveComments(cms));
		}
		else {
			return ResponseEntity.ok().body(null);
		}
	}
}
