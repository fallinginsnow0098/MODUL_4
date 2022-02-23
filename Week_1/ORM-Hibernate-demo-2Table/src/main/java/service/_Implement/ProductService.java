package service._Implement;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import service.IProductService;

import java.util.ArrayList;

public class ProductService implements IProductService {
    @Autowired
    private IProductService productService;
    @Override
    public ArrayList<Product> findAll() {
        return productService.findAll();
    }

    @Override
    public Product save(Product product) {
        return productService.save(product);
    }

    @Override
    public Product delete(int id) {
        return productService.delete(id);
    }

    @Override
    public Product findById(int id) {
        return productService.findById(id);
    }
}
