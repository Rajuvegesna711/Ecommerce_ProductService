package com.vegesna.ProductService.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long Id;
    private Timestamp createdAt;
    private Timestamp updatedAt;



}
