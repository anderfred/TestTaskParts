package com.example.demo.service;

import com.example.demo.entity.Part;

import java.util.Optional;

public interface PartService {
    Iterable<Part> findAll();
    void deleteById(Integer integer);
    <S extends Part> S save(S s);
    Optional<Part> findById(Integer integer);
    int compQty();
}
