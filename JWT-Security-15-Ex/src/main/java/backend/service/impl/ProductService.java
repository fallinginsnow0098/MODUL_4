package backend.service.impl;

import backend.model.Category;
import backend.model.Product;
import backend.repository.IProductRepository;
import backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Iterable<Product> findByName(String searchName) {
        return productRepository.findAllByNameContaining(searchName);
    }

    @Override
    public Iterable<Product> findByCategoryId(Long idCategory) {
        return productRepository.findAllByCategory_Id(idCategory);
    }
    @Override
    public Page<Product> findPage(Pageable pageable){
        return productRepository.findAll(pageable);
    }
}
