package com.vegesna.ProductService.Common;

import com.vegesna.ProductService.DTO.UserDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class authCommon {
    private RestTemplate restTemplate;

    authCommon(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    public UserDTO validate(String tokenValue) throws URISyntaxException {
        URI uri = new URI("http://localhost:8081/user/validate/"+tokenValue);
        RequestEntity<?> requestEntity = new RequestEntity<>(new HttpHeaders(),HttpMethod.GET, uri);
        ResponseEntity<UserDTO> responseEntity = restTemplate.exchange(requestEntity,UserDTO.class);
        if(!responseEntity.hasBody()){
            throw new RuntimeException("Invalid token");
        }
        if(responseEntity!=null&&responseEntity.getStatusCode().equals(HttpStatus.OK)&&responseEntity.getBody()!=null){
            return responseEntity.getBody();
        }
        return null;
    }

}
