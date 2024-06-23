package com.vegesna.ProductService.DTO;

import lombok.Data;

@Data
public class FakeStoreResponseDTO {
    public Long Id;
    public String title;
    public double price;
    public String description;
    public String category;
    public String image;
}
