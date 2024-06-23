package com.vegesna.ProductService.DTO;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ExceptionResponseDTO {
    private String message;
    private HttpStatus status;
}
