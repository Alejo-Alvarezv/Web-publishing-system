package com.alejo.WebPublishingSystem.restRepository;

import java.util.List;

import com.alejo.WebPublishingSystem.documentDAO.Comment;
import com.alejo.WebPublishingSystem.documentDAO.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

@Repository
public class PostRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * Save into a MongoDB
	 * @param post - Contains the information about post
	 * @return - An Object Post
	 */
	public Post save(Post post) {
		return mongoTemplate.save(post);
	}

	/**
	 * Add a comment in a post
	 * @param idPost - Identifier of Post
	 * @param comment - Comment to add
	 * @return - Object updated
	 */
	public UpdateResult addComment(String idPost, Comment comment) {
		return mongoTemplate.updateFirst(
				new Query().addCriteria(Criteria.where("_id").is(idPost)),
				new Update().addToSet("comments", comment),
				"Post"
		);
	}

	/**
	 * Get all post
	 * @return list with all post
	 */
	public List<Post> findAll(){
		return mongoTemplate.findAll(Post.class);
	}

	/**
	 * Find a specific post
	 * @param idPost - Identifier a post
	 * @return Post
	 */
	public Post find(String idPost){
		return mongoTemplate.find(
				new Query().addCriteria(Criteria.where("_id").is(idPost)), Post.class).get(0);
	}

	/**
	 * Search post
	 * @param search - Post to search
	 * @return List of post
	 */
	public List<Post> search(String search){
		return mongoTemplate.aggregate(Aggregation.newAggregation(
				Aggregation.match(new Criteria().orOperator(
						Criteria.where("name").regex(search),
						Criteria.where("text").regex(search)
						))
				), "Post", Post.class).getMappedResults();
	}
}
