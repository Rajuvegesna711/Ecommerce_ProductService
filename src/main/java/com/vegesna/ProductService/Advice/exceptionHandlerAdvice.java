package com.vegesna.ProductService.Advice;

import com.vegesna.ProductService.DTO.ExceptionResponseDTO;
import com.vegesna.ProductService.Exception.categoryIsEmpty;
import com.vegesna.ProductService.Exception.categoryNotFound;
import com.vegesna.ProductService.Exception.productIsEmpty;
import com.vegesna.ProductService.Exception.productNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class exceptionHandlerAdvice {

    @ExceptionHandler(productNotFound.class)
    public ResponseEntity<ExceptionResponseDTO> handleProductNotFoundException(){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO();
        exceptionResponseDTO.setStatus(HttpStatus.NOT_FOUND);
        exceptionResponseDTO.setMessage("Product not found");
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(categoryNotFound.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryNotFoundException(){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO();
        exceptionResponseDTO.setStatus(HttpStatus.NOT_FOUND);
        exceptionResponseDTO.setMessage("Category not found");
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(productIsEmpty.class)
    public ResponseEntity<ExceptionResponseDTO> handleProductIsEmptyException(){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO();
        exceptionResponseDTO.setStatus(HttpStatus.NOT_FOUND);
        exceptionResponseDTO.setMessage("Product is empty...");
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(categoryIsEmpty.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryIsEmptyException(){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO();
        exceptionResponseDTO.setStatus(HttpStatus.NOT_FOUND);
        exceptionResponseDTO.setMessage("Category is empty...");
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }


}
