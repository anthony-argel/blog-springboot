package com.anthonyargel.blog.Controllers;

import com.anthonyargel.blog.Models.Category;
import com.anthonyargel.blog.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category/admin")
public class AdminCategoryController {
    private final CategoryService categoryService ;

    @Autowired
    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            Category newCategory = categoryService.createCategory(new Category(category.getName()));
            return new ResponseEntity<>(newCategory, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @ModelAttribute Category category) {
        System.out.println("LOOKING FOR CATEGORY");
        Category currentCategory = categoryService.getCategoryById(id);
        if(currentCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentCategory.setName(category.getName());
        try {
            Category updatedCategory = categoryService.updateCategory(currentCategory);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        Category currentCategory = categoryService.getCategoryById(id);
        if(currentCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new String("Category Deleted"), HttpStatus.OK);
    }
}
