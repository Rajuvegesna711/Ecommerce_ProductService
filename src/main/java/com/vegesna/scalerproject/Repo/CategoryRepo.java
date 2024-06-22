package com.vegesna.scalerproject.Repo;

import com.vegesna.scalerproject.Models.Category;
import com.vegesna.scalerproject.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category save(Category category);
}
