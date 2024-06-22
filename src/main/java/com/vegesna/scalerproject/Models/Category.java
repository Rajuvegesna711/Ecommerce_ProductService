package com.vegesna.scalerproject.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category extends BaseModel {
    private String categoryName;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    public static Category tobuild(String categoryName){
        return Category.builder().categoryName(categoryName).build();
    }

}
