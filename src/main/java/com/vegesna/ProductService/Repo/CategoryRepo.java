package com.vegesna.ProductService.Repo;

import com.vegesna.ProductService.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category save(Category category);
}
