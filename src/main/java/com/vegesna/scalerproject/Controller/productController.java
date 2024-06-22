package com.vegesna.scalerproject.Controller;
import com.vegesna.scalerproject.Exception.categoryIsEmpty;
import com.vegesna.scalerproject.Exception.categoryNotFound;
import com.vegesna.scalerproject.Exception.productIsEmpty;
import com.vegesna.scalerproject.Exception.productNotFound;
import com.vegesna.scalerproject.Models.Product;
import com.vegesna.scalerproject.Service.FakeStoreProductService;
import com.vegesna.scalerproject.Service.productService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class productController {
    private productService productService;

    productController(@Qualifier("PrimaryProductService") productService productService){
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable long id) throws URISyntaxException, productNotFound, productIsEmpty {
        return productService.getProductBYID(id);
    }
    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) throws URISyntaxException, categoryNotFound, categoryIsEmpty {
        return productService.createProduct(product);
    }

    @PutMapping("/")
    public Product updateProduct (@RequestBody Product product) throws URISyntaxException, productNotFound {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable long id) throws URISyntaxException, productNotFound {
        return productService.deleteProduct(id);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() throws URISyntaxException {
        return productService.getAllProducts();
    }
}
