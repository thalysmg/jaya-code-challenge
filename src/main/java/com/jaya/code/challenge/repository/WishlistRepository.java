package com.jaya.code.challenge.repository;

import com.jaya.code.challenge.domain.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {
}
