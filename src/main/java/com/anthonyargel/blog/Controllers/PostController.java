package com.anthonyargel.blog.Controllers;

import com.anthonyargel.blog.DTOs.CreatePostDTO;
import com.anthonyargel.blog.DTOs.ReturnPostDTO;
import com.anthonyargel.blog.Models.Category;
import com.anthonyargel.blog.Models.Post;
import com.anthonyargel.blog.Services.CategoryService;
import com.anthonyargel.blog.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/public")
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;

    @Autowired
    public PostController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postService.getAllPosts(pageable);
    }

    @GetMapping("/category/{categoryName}")
    public Page<Post> getPostsByCategoryName(@PathVariable String categoryName, Pageable pageable) {
        return postService.getAllPostsByCategoryName(categoryName, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnPostDTO> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if(post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Category postCategory = categoryService.getCategoryById(post.getCategory().getId());
        return new ResponseEntity<>(new ReturnPostDTO(post, postCategory), HttpStatus.OK);
    }
}
