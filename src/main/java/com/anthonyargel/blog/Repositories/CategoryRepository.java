package com.anthonyargel.blog.Repositories;

import com.anthonyargel.blog.Models.Category;
import com.anthonyargel.blog.Projection.CategoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c.id as id, c.name as name FROM Category c")
    Page<CategoryProjection> getAllCategoriesUsingProjection(Pageable pageable);
}
