package com.example.happytownclone.commons.gateway;

import java.util.List;
import java.util.Optional;

public interface BaseGateway<T, I> {
    T create(T model);

    Optional<T> findById(I id);

    List<T> findAll();

    T update(T model);

    void deleteById(I id);
}
