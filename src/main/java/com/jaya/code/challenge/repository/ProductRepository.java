package com.jaya.code.challenge.repository;

import com.jaya.code.challenge.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
