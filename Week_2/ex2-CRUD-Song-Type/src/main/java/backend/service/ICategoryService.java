package backend.service;

import backend.model.Category;

import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAll();
    Category save(Category category);
    Optional<Category> findById(long id);
}
