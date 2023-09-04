package com.jaya.code.challenge.mapping;

import com.jaya.code.challenge.controller.dto.WishlistDTO;
import com.jaya.code.challenge.domain.Wishlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface WishlistMapper extends EntityMapper<Wishlist, WishlistDTO> {

}
