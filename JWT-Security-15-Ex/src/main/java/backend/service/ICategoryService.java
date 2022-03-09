package backend.service;

import backend.model.Category;

public interface ICategoryService {
    Iterable<Category> findAll();
    Iterable<Category> findAllByName(String name);
}
