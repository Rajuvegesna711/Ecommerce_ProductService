package com.vegesna.ProductService.Service;

import com.vegesna.ProductService.Exception.categoryIsEmpty;
import com.vegesna.ProductService.Exception.categoryNotFound;
import com.vegesna.ProductService.Exception.productIsEmpty;
import com.vegesna.ProductService.Exception.productNotFound;
import com.vegesna.ProductService.Models.Product;

import java.net.URISyntaxException;
import java.util.List;

public interface productService{
    Product getProductBYID(long id) throws URISyntaxException, productNotFound, productIsEmpty;
    Product createProduct(Product product) throws URISyntaxException, categoryNotFound, categoryIsEmpty;
    Product updateProduct(Product product) throws URISyntaxException, productNotFound;
    Product deleteProduct(long id) throws URISyntaxException, productNotFound;
    List<Product> getAllProducts() throws URISyntaxException;
}
