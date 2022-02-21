package service;

import model.Product;

import java.util.List;

public interface IService {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
    void update(Product product, int id);
    void remove(int id);

    boolean checkProduct(int id);
    Product search(String name);
}
