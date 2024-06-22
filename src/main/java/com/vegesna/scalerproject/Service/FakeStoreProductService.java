package com.vegesna.scalerproject.Service;

import com.vegesna.scalerproject.DTO.FakeStoreRequestDTO;
import com.vegesna.scalerproject.Exception.productNotFound;
import com.vegesna.scalerproject.Models.Category;
import com.vegesna.scalerproject.Models.Product;
import com.vegesna.scalerproject.DTO.FakeStoreResponseDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
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

@Service("FakeStoreProductService")
public class FakeStoreProductService implements productService{

    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductBYID(long id) throws URISyntaxException, productNotFound {
        URI uri = new URI("https://fakestoreapi.com/products/"+id);
        RequestEntity<?> requestEntity = new RequestEntity<>(HttpMethod.GET,uri);
       ResponseEntity<FakeStoreResponseDTO> responseEntity = restTemplate.exchange(requestEntity, FakeStoreResponseDTO.class);
        if(responseEntity!=null&&responseEntity.getStatusCode().equals(HttpStatus.OK)&&responseEntity.getBody()!=null){
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
