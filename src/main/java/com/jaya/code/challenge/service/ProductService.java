package com.jaya.code.challenge.service;

import com.jaya.code.challenge.domain.Product;
import com.jaya.code.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product findById(String id) {
        return repository.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Produto não cadastrado"));
    }

    public void saveAll(List<Product> products) {
        repository.saveAll(products);
    }

    public boolean collectionIsEmpty() {
        return repository.findAll().isEmpty();
    }
}
