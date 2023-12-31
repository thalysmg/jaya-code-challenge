package com.jaya.code.challenge.service;

import com.jaya.code.challenge.domain.Product;
import com.jaya.code.challenge.domain.Wishlist;
import com.jaya.code.challenge.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository repository;

    @Autowired
    private ProductService productService;

    public Wishlist findById(String id) {
        return repository.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Wishlist não existe"));
    }

    public void save(Wishlist wishlist) {
        repository.save(wishlist);
    }

    public void saveAll(List<Wishlist> wishlists) {
        repository.saveAll(wishlists);
    }

    public void addProduct(String wishlistId, String productId) {
        Wishlist wishlist = findById(wishlistId);

        if (wishlist.limitExceeded())
            throw new HttpClientErrorException(BAD_REQUEST, "Não foi possível adicionar o produto na lista. Limite máximo já foi atingido!");

        wishlist.addNewProduct(productService.findById(productId));
        repository.save(wishlist);
    }

    public List<Product> getAllProductsFromWishlist(String wishlistId) {
        return findById(wishlistId).getProducts();
    }

    public void removeProductFromWishlist(String wishlistId, String productId) {
        Wishlist wishlist = findById(wishlistId);
        productService.findById(productId);

        if (wishlist.productIsNotInList(productId))
            throw new HttpClientErrorException(NOT_FOUND, "O produto informado não está nesta lista de desejos.");

        wishlist.removeProduct(productId);
        repository.save(wishlist);
    }
}
