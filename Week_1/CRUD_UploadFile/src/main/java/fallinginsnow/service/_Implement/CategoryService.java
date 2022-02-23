package fallinginsnow.service._Implement;

import fallinginsnow.model.Category;
import fallinginsnow.service.ICategoryService;

import java.util.ArrayList;

public class CategoryService implements ICategoryService {
    private static ArrayList<Category> categories = new ArrayList<>();
    private static int id_auto = 0;

    static {
        categories = new ArrayList<>();
        categories.add(new Category(id_auto++, "SmartPhone"));
        categories.add(new Category(id_auto++, "Tablet"));
    }
    @Override
    public ArrayList<Category> getAllCategory() {
        return categories;
    }

    @Override
    public String saveCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategory(Category category) {

    }

    @Override
    public Category getCategory(int id) {
        for (Category c : categories) {
            if (c.getC_id() == id){
                return c;
            }
        }
        return null;
    }
}
