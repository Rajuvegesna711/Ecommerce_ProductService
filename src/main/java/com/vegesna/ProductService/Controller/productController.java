package com.vegesna.ProductService.Controller;
import com.vegesna.ProductService.Exception.categoryIsEmpty;
import com.vegesna.ProductService.Exception.categoryNotFound;
import com.vegesna.ProductService.Exception.productIsEmpty;
import com.vegesna.ProductService.Exception.productNotFound;
import com.vegesna.ProductService.Common.authCommon;
import com.vegesna.ProductService.Models.Product;
import com.vegesna.ProductService.Service.productService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class productController {
    @Autowired
    private productService productService;
    private authCommon authCommon;

    productController(@Qualifier("FakeStoreProductService") productService productService, authCommon authCommon){
        this.productService = productService;
        this.authCommon = authCommon;
    }


    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable long id) throws URISyntaxException, productNotFound, productIsEmpty {
        // if(authCommon.validate(auth)!=null){
            return productService.getProductBYID(id);
        // }@RequestHeader("Auth") String auth
        // return null;
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
