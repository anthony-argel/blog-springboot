package com.anthonyargel.blog.Controllers;

import com.anthonyargel.blog.DTOs.CreatePostDTO;
import com.anthonyargel.blog.DTOs.ReturnPostDTO;
import com.anthonyargel.blog.Models.Category;
import com.anthonyargel.blog.Models.Post;
import com.anthonyargel.blog.Services.CategoryService;
import com.anthonyargel.blog.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("ADMIN")
@RequestMapping("/post/admin")
public class AdminPostController {
    private final PostService postService;
    private final CategoryService categoryService;
    @Autowired
    public AdminPostController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ReturnPostDTO> createPost(@RequestBody CreatePostDTO createPostDTO) {
        try {
            Category categoryToSet = categoryService.getCategoryById(createPostDTO.getCategoryId());
            if(categoryToSet == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Post newPost = postService.createPost(new Post(createPostDTO.getTitle(), categoryToSet, createPostDTO.getBannerImage(), createPostDTO.getContent(), createPostDTO.getSummary()));
            return new ResponseEntity<>(new ReturnPostDTO(newPost, categoryToSet), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody CreatePostDTO post) {
        try {
            Post currentPost = postService.getPostById(id);
            Category categoryToSet = categoryService.getCategoryById(post.getCategoryId());
            if(currentPost == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            currentPost.setTitle(post.getTitle());
            currentPost.setCategory(categoryToSet);
            currentPost.setBannerImage(post.getBannerImage());
            currentPost.setContent(post.getContent());
            currentPost.setSummary(post.getSummary());

            Post updatedPost = postService.updatePost(currentPost);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        Post currentPost = postService.getPostById(id);
        if(currentPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.deletePost(id);
        return new ResponseEntity<>(new String("Category Deleted"), HttpStatus.OK);
    }

}
