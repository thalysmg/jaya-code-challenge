package com.jaya.code.challenge.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -18985783706082105L;

    private String id;

    private String owner;

    private LocalDate lastUpdate;

    private List<ProductDTO> products;
}
