package com.jaya.code.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {

    @Id
    private String id;

    private String owner;

    private LocalDate lastUpdate = LocalDate.now();

    private static final int LIMITE_MAX_PRODUTOS = 20;

    @DocumentReference
    private List<Product> products;

    public void addNewProduct(Product product) {
        if (products == null)
            products = new ArrayList<>();
        products.add(product);
    }

    public boolean limitExceeded() {
        return products.size() >= LIMITE_MAX_PRODUTOS;
    }

    public boolean existsProductInList(String productId) {
        return products.stream().anyMatch(p -> p.getId().equals(productId));
    }

    public boolean productIsNotInList(String productId) {
        return products.stream().noneMatch(p -> p.getId().equals(productId));
    }

    public void removeProduct(String productId) {
        products = products.stream().filter(p -> !p.getId().equals(productId)).toList();
    }
}
