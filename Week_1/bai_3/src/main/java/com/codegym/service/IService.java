package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IService {
    List<Product> findAll();

    void save(Product product);

    Product findById(int id);

    void update(int id, Product product);

    void remove(int id);
}
