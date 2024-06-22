package com.vegesna.scalerproject.Service;

import com.vegesna.scalerproject.Exception.categoryIsEmpty;
import com.vegesna.scalerproject.Exception.categoryNotFound;
import com.vegesna.scalerproject.Exception.productIsEmpty;
import com.vegesna.scalerproject.Exception.productNotFound;
import com.vegesna.scalerproject.Models.Product;

import java.net.URISyntaxException;
import java.util.List;

public interface productService{
    Product getProductBYID(long id) throws URISyntaxException, productNotFound, productIsEmpty;
    Product createProduct(Product product) throws URISyntaxException, categoryNotFound, categoryIsEmpty;
    Product updateProduct(Product product) throws URISyntaxException, productNotFound;
    Product deleteProduct(long id) throws URISyntaxException, productNotFound;
    List<Product> getAllProducts() throws URISyntaxException;
}
