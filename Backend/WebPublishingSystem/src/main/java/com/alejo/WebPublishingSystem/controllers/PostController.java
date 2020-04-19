package com.alejo.WebPublishingSystem.controllers;

import java.util.List;

import com.alejo.WebPublishingSystem.documentDAO.Comment;
import com.alejo.WebPublishingSystem.documentDAO.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejo.WebPublishingSystem.restRepository.PostRepository;
import com.mongodb.client.result.UpdateResult;

@RestController
@RequestMapping("/web-api/")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PostController {
	
	@Autowired
	private PostRepository postRepository;

	/**
	 * List all post
	 * @return List with all post
	 */
	@GetMapping("/posts/")
	public List<Post> findAll(){
		return postRepository.findAll();
	}

	/**
	 * Save a post
	 * @param post - Information new post
	 * @return Post created
	 */
	@PostMapping("/posts")
	public Post save(@RequestBody Post post) {
		return postRepository.save(post);
	}

	/**
	 * Add comment to a post
	 * @param idPost - Identifier of a post
	 * @param comment New comment to add
	 * @return Object updated
	 */
	@PostMapping("/posts/{idPost}/addComment")
	public UpdateResult addComment(@PathVariable("idPost") String idPost, @RequestBody Comment comment) {
		return postRepository.addComment(idPost, comment);
	}

	/**
	 * Find a specific post
	 * @param idPost - Identifier
	 * @return Object Post
	 */
	@GetMapping("/post/{idPost}")
	public Post find(@PathVariable("idPost") String idPost) {
		return postRepository.find(idPost);
	}

	/**
	 *  Search posts
	 * @param searchPost - Post to search
	 * @return List of post
	 */
	@GetMapping("/post/search/{searchPost}")
	public List<Post> search(@PathVariable("searchPost") String searchPost) {
		return postRepository.search(searchPost);
	}
}
