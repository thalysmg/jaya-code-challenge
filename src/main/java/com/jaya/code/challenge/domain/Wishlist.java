package com.jaya.code.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {

    @Id
    private String id;

    private String owner;

    private LocalDate lastUpdate = LocalDate.now();

    @DocumentReference
    private List<Product> products;


}
