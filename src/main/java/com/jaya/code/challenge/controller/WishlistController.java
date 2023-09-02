package com.jaya.code.challenge.controller;

import com.jaya.code.challenge.domain.Product;
import com.jaya.code.challenge.domain.Wishlist;
import com.jaya.code.challenge.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService service;

    @GetMapping("/{id}")
    public ResponseEntity<Void> existsProductInWishlist(@PathVariable String id, @RequestParam String productId) {
        if (service.existsProductInList(id, productId)) {
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public void saveNewWishList(@RequestBody Wishlist wishlist) {
        service.save(wishlist);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> findAllProductsFromWishlist(@PathVariable String id) {
        return ResponseEntity.ok(service.getAllProductsFromWishlist(id));
    }

    @PostMapping("/{id}")
    @ResponseStatus(CREATED)
    public void addNewProductInList(@PathVariable String id, @RequestParam String productId) {
        service.addProduct(id, productId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteById(@PathVariable String id, @RequestParam String productId) {
        service.removeProductFromWishlist(id, productId);
    }
}
