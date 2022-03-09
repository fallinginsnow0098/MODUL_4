package backend.service;

import backend.model.Category;
import backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
    void delete(Long id);
    Product save(Product product);
    Iterable<Product> findByName(String searchName);
    Iterable<Product> findByCategoryId(Long idCategory);

    Page<Product> findPage(Pageable pageable);
}
