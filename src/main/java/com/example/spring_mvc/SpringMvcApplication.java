package com.example.spring_mvc;

import com.example.spring_mvc.entities.Product;
import com.example.spring_mvc.repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepo productRepo){
        return args ->{
            productRepo.save(Product.builder()
                    .name("Phone")
                    .price(6000)
                    .quantity(11)
                    .build());
            productRepo.save(Product.builder()
                    .name("Laptop")
                    .price(18000)
                    .quantity(5)
                    .build());
            productRepo.save(Product.builder()
                    .name("LG TV")
                    .price(15000)
                    .quantity(10)
                    .build());
            productRepo.save(Product.builder()
                    .name("AirPods")
                    .price(2500)
                    .quantity(15)
                    .build());
            productRepo.findAll().forEach(p->{System.out.println(p.toString());});
        };
    }

}
