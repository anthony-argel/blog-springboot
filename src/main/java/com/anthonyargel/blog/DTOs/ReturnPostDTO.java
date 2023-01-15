package com.anthonyargel.blog.DTOs;

import com.anthonyargel.blog.Models.Category;
import com.anthonyargel.blog.Models.Post;

public class ReturnPostDTO {
    private Post post;
    private Category category;

    public ReturnPostDTO(Post post, Category category) {
        this.post = post;
        this.category = category;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
