package com.vegesna.ProductService;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);

//        List<Integer> z = new ArrayList<>(Arrays.asList(1,2,3,4,5));
//        Iterator<?> x = z.iterator();
//        while (x.hasNext()){
//            System.out.println(x.next());
//        }

    }

    @PostConstruct
    void init(){
        System.out.println("kjfhdwksjfhkjsdhf");
    }


}
