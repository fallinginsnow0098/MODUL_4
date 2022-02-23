package service._Implement;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import service.ICategoryService;

import java.util.ArrayList;

public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryService categoryService;
    @Override
    public ArrayList<Category> findAll() {
        return categoryService.findAll();
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public Category delete(int id) {
        return null;
    }

    @Override
    public Category findById(int id) {
        return categoryService.findById(id);
    }
}
