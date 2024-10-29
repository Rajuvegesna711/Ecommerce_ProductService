package com.vegesna.ProductService.Service;

import com.vegesna.ProductService.DTO.FakeStoreRequestDTO;
import com.vegesna.ProductService.Exception.productNotFound;
import com.vegesna.ProductService.Models.Category;
import com.vegesna.ProductService.Models.Product;
import com.vegesna.ProductService.DTO.FakeStoreResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service("FakeStoreProductService")
public class FakeStoreProductService implements productService{

    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate){
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductBYID(long id) throws URISyntaxException, productNotFound {

        Product p = (Product)redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_"+id);
        // Cache Hit
        if(p!=null){
            return p;
        }

        URI uri = new URI("https://fakestoreapi.com/products/"+id);
        RequestEntity<?> requestEntity = new RequestEntity<>(HttpMethod.GET,uri);
       ResponseEntity<FakeStoreResponseDTO> responseEntity = restTemplate.exchange(requestEntity, FakeStoreResponseDTO.class);
        if(responseEntity!=null&&responseEntity.getStatusCode().equals(HttpStatus.OK)&&responseEntity.getBody()!=null){
            //cache Miss.. So, storing in cache
            redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_"+id, convertFakeStoreResponseDTOtoProduct(responseEntity.getBody()));
            return convertFakeStoreResponseDTOtoProduct(responseEntity.getBody());
        }
        if(responseEntity.getBody()==null){
            throw new productNotFound("Product not found");

        }
        return null;
    }


    @Override
    public Product createProduct(Product product) throws URISyntaxException{
        URI uri = new URI("https://fakestoreapi.com/products/");
        FakeStoreRequestDTO fakeStoreRequestDTO = convertProductToFakeStoreRequestDTO(product);
        RequestEntity<?> requestEntity = new RequestEntity<>(fakeStoreRequestDTO,  HttpMethod.POST, uri);
        ResponseEntity<FakeStoreResponseDTO> responseEntity = restTemplate.exchange(requestEntity, FakeStoreResponseDTO.class);
        if(responseEntity!=null && responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.getBody()!=null){
            return convertFakeStoreResponseDTOtoProduct(responseEntity.getBody());
        }
        if(responseEntity.equals(null)){
            throw new RuntimeException("Product is not available");
        }
        return null;

    }

    @Override
    public Product updateProduct(Product product) throws URISyntaxException, productNotFound{
        URI uri = new URI("https://fakestoreapi.com/products/"+product.getId());
        FakeStoreRequestDTO fakeStoreRequestDTO = convertProductToFakeStoreRequestDTO(product);
        RequestEntity<?> requestEntity = new RequestEntity<>(fakeStoreRequestDTO, HttpMethod.PUT,uri);
        ResponseEntity<FakeStoreResponseDTO> responseEntity = restTemplate.exchange(requestEntity,FakeStoreResponseDTO.class);
        if(responseEntity!=null&&responseEntity.getStatusCode().equals(HttpStatus.OK)&&responseEntity.getBody()!=null){
            return convertFakeStoreResponseDTOtoProduct(responseEntity.getBody());
        }
        if(responseEntity.getBody()==null){
            throw new productNotFound("Product not found");
        }
        return null;
    }

    @Override
    public Product deleteProduct(long id) throws URISyntaxException, productNotFound{
        URI uri = new URI("https://fakestoreapi.com/products/"+id);
        RequestEntity requestEntity = new RequestEntity(HttpMethod.DELETE,uri);
        ResponseEntity<FakeStoreResponseDTO> responseEntity = restTemplate.exchange(requestEntity, FakeStoreResponseDTO.class);
        if(responseEntity!=null && responseEntity.getStatusCode().equals(HttpStatus.OK)&&responseEntity.getBody()!=null){
            return convertFakeStoreResponseDTOtoProduct(responseEntity.getBody());
        }
        if(responseEntity.getBody()==null){
            throw new productNotFound("Product not found");
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws URISyntaxException {
        URI uri = new URI("https://fakestoreapi.com/products/");
        ParameterizedTypeReference<List<FakeStoreResponseDTO>> typeReference = new ParameterizedTypeReference<List<FakeStoreResponseDTO>>() {};
        RequestEntity<?> requestEntity = new RequestEntity<>(HttpMethod.GET, uri);
        ResponseEntity<List<FakeStoreResponseDTO>> responseEntity = restTemplate.exchange(requestEntity, typeReference);
        if(responseEntity!=null && responseEntity.getStatusCode().equals(HttpStatus.OK) && responseEntity.getBody()!=null){
            List<FakeStoreResponseDTO> response = responseEntity.getBody();
            List<Product> result  = new ArrayList<>();
            response.stream().forEach(w -> {
                result.add(convertFakeStoreResponseDTOtoProduct(w));
            });
            return result;
        }
        return null;
    }


    Product convertFakeStoreResponseDTOtoProduct(FakeStoreResponseDTO fakeStoreResponsedto){
        return new Product().toModel(fakeStoreResponsedto.Id,fakeStoreResponsedto.title,
                fakeStoreResponsedto.price, fakeStoreResponsedto.description,
                Category.tobuild(fakeStoreResponsedto.category), fakeStoreResponsedto.image);
    }

    FakeStoreRequestDTO convertProductToFakeStoreRequestDTO(Product product){

        return new FakeStoreRequestDTO(product.getTitle(), product.getPrice(),
                product.getDescription(),String.valueOf(product.getCategory()), product.getImage());
    }
}
