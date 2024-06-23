package com.vegesna.ProductService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Product extends BaseModel {
    private String title;
    private Double price;
    private String description;
    @ManyToOne
    @JoinColumn
    private Category category;
    private String image;

    public static Product toModel(Long Id, String title, Double price, String description, Category category, String image){
        return Product.builder().Id(Id).title(title).price(price).description(description).category(category).image(image).build();
    }
}
