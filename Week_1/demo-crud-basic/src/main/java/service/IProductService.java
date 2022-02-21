package service;

import model.Category;
import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product getProduct(int idProduct);
    void save(Product product);
    void delete(int idProduct);
    void delete(Product product);
    List<Category> getAllCategories();
    Category getCategory(int id);
}
