package com.vegesna.ProductService.Repo;

import com.vegesna.ProductService.Models.Product;
import com.vegesna.ProductService.Projection.productWithTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{
        @Override
        Optional<Product> findById(Long Id);

        Optional<Product> findByTitle(String title);
        //List can be empty, but it won't throw null pointer exception
        List<Product> findByTitleAndAndDescription(String title, String description);

        Product save(Product product);

        //HQL --> Hibernate Query Language (OOPs + SQL)

        @Query("select p.title as Title, p.description as Description from Product p where p.Id=:id" +
                "")
        productWithTitleAndDescription titleAndDescriptionQuery(@Param("id") Long id);

        //SQL --> Structured Query Language
        @Query(value = "select p.title as Title, p.description as Description from Product p where p.Id=:id", nativeQuery = true)
        productWithTitleAndDescription titleAndDescriptionQuery1(@Param("id") Long id);
}
