package com.anthonyargel.blog.Controllers;

import com.anthonyargel.blog.Models.Category;
import com.anthonyargel.blog.Models.Post;
import com.anthonyargel.blog.Projection.CategoryProjection;
import com.anthonyargel.blog.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/public")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public Page<CategoryProjection> getAllCategories(Pageable pageable) {
        return categoryService.getAllCategoriesUsingProjection(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getUser(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if(category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
