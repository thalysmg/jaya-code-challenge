package com.jaya.code.challenge.mapping;

import java.util.List;

public interface EntityMapper <E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtos);

    List<D> toDtos(List<E> entities);
}
