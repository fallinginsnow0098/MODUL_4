package backend.service.impl;

import backend.model.Category;
import backend.repository.ICategoryRepository;
import backend.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Iterable<Category> findAllByName(String name) {
        return categoryRepository.findAllByNameContaining(name);
    }
}
