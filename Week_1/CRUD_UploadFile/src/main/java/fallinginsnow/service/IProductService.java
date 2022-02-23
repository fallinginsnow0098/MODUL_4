package fallinginsnow.service;

import fallinginsnow.model.Product;

import java.util.ArrayList;

public interface IProductService {
    ArrayList<Product> getAllProduct();

    String saveProduct(Product product);

    void deleteProduct(Product product);

    Product getProduct(int id);
}
