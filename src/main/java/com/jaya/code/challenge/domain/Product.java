package com.jaya.code.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
public class Product {

    @Id
    private String id;

    private String name;

    private Double price;
}
