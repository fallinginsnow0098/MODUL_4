package cg.repository;

import cg.model.Product;

import java.util.ArrayList;

public interface IProductRepository {
    ArrayList<Product> findAll();

    Product saveProduct(Product product);
    ArrayList<Product> findByKeyword(String keyword);

    Product deleteProduct(int id);

    Product findProductById(int id);
}
