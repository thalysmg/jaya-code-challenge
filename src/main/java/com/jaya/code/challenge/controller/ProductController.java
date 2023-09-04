package com.jaya.code.challenge.controller;

import com.jaya.code.challenge.domain.Product;
import com.jaya.code.challenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/init")
    @ResponseStatus(NO_CONTENT)
    public void existsProductInWishlist() {
        if (service.collectionIsEmpty()) {
            Product p1 = Product.builder().name("Notebook").price(3213.99).build();
            Product p2 = Product.builder().name("Xbox One S").price(2800.99).build();
            Product p3 = Product.builder().name("Playstation 5").price(3599.99).build();
            Product p4 = Product.builder().name("Placa de Vídeo").price(1599.99).build();
            service.saveAll(List.of(p1, p2, p3, p4));
        }
    }
}
