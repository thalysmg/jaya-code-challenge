package com.jaya.code.challenge.mapping;

import com.jaya.code.challenge.controller.dto.ProductDTO;
import com.jaya.code.challenge.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<Product, ProductDTO> {

}
