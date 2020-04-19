package com.alejo.WebPublishingSystem.controllers;

import java.util.List;

import com.alejo.WebPublishingSystem.Document.dao.Comment;
import com.alejo.WebPublishingSystem.Document.dao.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejo.WebPublishingSystem.RestRepository.PostRepository;
import com.mongodb.client.result.UpdateResult;

@RestController
@RequestMapping("/web-api/")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/posts/")
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	@PostMapping("/posts")
	public Post save(@RequestBody Post post) {
		return postRepository.save(post);
	}
	
	@PostMapping("/posts/{idPost}/addComment")
	public UpdateResult addComment(@PathVariable("idPost") String idPost, @RequestBody Comment comment) {
		return postRepository.addComment(idPost, comment);
	}
	
	@GetMapping("/post/{idPost}")
	public Post find(@PathVariable("idPost") String idPost) {
		return postRepository.find(idPost);
	}
	
	@GetMapping("/post/search/{searchPost}")
	public List<Post> search(@PathVariable("searchPost") String searchPost) {
		return postRepository.search(searchPost);
	}
	
	

}
