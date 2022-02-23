package cg.service;

import cg.model.Product;
import cg.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ArrayList<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Product delete(int id) {
        return productRepository.deleteProduct(id);
    }
}
