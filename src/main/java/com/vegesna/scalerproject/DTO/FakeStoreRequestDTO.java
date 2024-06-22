package com.vegesna.scalerproject.DTO;

import com.vegesna.scalerproject.Models.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Builder
@Data
public class FakeStoreRequestDTO {
    public String title;
    public double price;
    public String description;
    public String category;
    public String image;

    FakeStoreRequestDTO toDTO(String title, double price, String description, Category category,String image){
        return FakeStoreRequestDTO.builder().title(title).price(price).description(description).category(String.valueOf(category)).image(image).build();
    }

}
