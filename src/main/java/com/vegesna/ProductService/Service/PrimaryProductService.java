package com.vegesna.ProductService.Service;

import com.vegesna.ProductService.Exception.categoryIsEmpty;
import com.vegesna.ProductService.Exception.categoryNotFound;
import com.vegesna.ProductService.Exception.productIsEmpty;
import com.vegesna.ProductService.Exception.productNotFound;
import com.vegesna.ProductService.Models.Category;
import com.vegesna.ProductService.Models.Product;
import com.vegesna.ProductService.Repo.CategoryRepo;
import com.vegesna.ProductService.Repo.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;



@Service("PrimaryProductService")
public class PrimaryProductService implements productService{

    ProductRepo productRepo;
    CategoryRepo categoryRepo;


    PrimaryProductService(ProductRepo productRepo, CategoryRepo categoryRepo){
        this.productRepo=productRepo;
        this.categoryRepo=categoryRepo;
    }

    @Override
    public Product getProductBYID(long id) throws productNotFound,productIsEmpty {
        if(productRepo.findById(id).isEmpty()){
            throw new productNotFound("Product not found");
        }
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isEmpty()){
            throw new productNotFound("Product not found");
        }
        if(optionalProduct.get().getTitle()==null){
            throw new productIsEmpty("product is empty...");
        }
        return optionalProduct.get();
    }

    @Override
    public Product createProduct(Product product) throws categoryNotFound, categoryIsEmpty {
        Category category = product.getCategory();
        if(category.getId()==null){
            Category savedCategory = categoryRepo.save(category);
            product.setCategory(savedCategory);
        }
        else if(categoryRepo.findById(category.getId()).isEmpty()){
            throw new categoryNotFound("category does not exist");
        }
        Optional<Category> optionalCategory = categoryRepo.findById(category.getId());
        if(optionalCategory.isEmpty()) {
            throw new categoryNotFound("category does not exist");
        }
        if(optionalCategory.get().getCategoryName().isEmpty()){
            throw new categoryIsEmpty("category is empty...");
        }
        product.setCategory(optionalCategory.get());
        Product saveProduct = productRepo.save(product);
        return saveProduct;
    }

    @Override
    public Product updateProduct(Product product) throws productNotFound {
        if(productRepo.findById(product.getId())==null){
            throw new productNotFound("Product not found");
        }
        Product saveProduct = productRepo.save(product);
        return saveProduct;
    }

    @Override
    public Product deleteProduct(long id) throws productNotFound {
        if(productRepo.findById(id)==null) {
            throw new productNotFound("Product not found");
        }
        productRepo.deleteById(id);
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws URISyntaxException {
        productRepo.findAll();
        return List.of();
    }
}
