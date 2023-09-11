package com.jaya.code.challenge.controller;

import com.jaya.code.challenge.controller.dto.ProductDTO;
import com.jaya.code.challenge.controller.dto.WishlistDTO;
import com.jaya.code.challenge.mapping.ProductMapper;
import com.jaya.code.challenge.mapping.WishlistMapper;
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

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService service;

    @Autowired
    private WishlistMapper mapper;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Void> existsProductInWishlist(@PathVariable String id, @RequestParam String productId) {
        if (service.findById(id).existsProductInList(productId))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveNewWishList(@RequestBody @Valid WishlistDTO wishlist) {
        service.save(mapper.toEntity(wishlist));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDTO>> findAllProductsFromWishlist(@PathVariable String id) {
        return ResponseEntity.ok(productMapper.toDtos(service.getAllProductsFromWishlist(id)));
    }

    @PostMapping("/{id}")
    @ResponseStatus(OK)
    public void addNewProductInList(@PathVariable String id, @RequestParam String productId) {
        service.addProduct(id, productId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteById(@PathVariable String id, @RequestParam String productId) {
        service.removeProductFromWishlist(id, productId);
    }
}
