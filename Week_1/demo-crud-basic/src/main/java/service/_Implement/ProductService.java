package service._Implement;

import model.Category;
import model.Product;
import service.IProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private static final ArrayList<Product> products = new ArrayList<>();
    private static final ArrayList<Category> categories = new ArrayList<>();
    private static int autoIncrementId = 1;


    static {
        products.add(new Product(++autoIncrementId,"Iphone 11 Pro Max",1000, "Good","Điện thoại"));
        products.add(new Product(++autoIncrementId,"Tablet Samsung 3",1200, "Good","Máy tính bảng"));
        products.add(new Product(++autoIncrementId,"Iphone 13 Pro Max",1600, "Best","Điện thoại"));
        products.add(new Product(++autoIncrementId,"Laptop Dell",1500, "Not bab","Laptop"));
        products.add(new Product(++autoIncrementId,"Laptop Acer",1100, "OK","Laptop"));
        categories.add(new Category(1,"Điện thoại"));
        categories.add(new Category(2,"Máy tính bảng"));
        categories.add(new Category(3,"Laptop"));
    }
    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getProduct(int idProduct) {
        Product product = null;
        for (Product p : products) {
            if (p.getId() == idProduct){
                product = p;
            }
        }
        return product;
    }

    @Override
    public void save(Product product) {
        if (product.getId() == 0) persit(product);
        else merge(product);
    }
    public Product merge(Product product){
        Product original = getProduct(product.getId());
        original.setName(product.getName());
        original.setPrice(product.getPrice());
        original.setDescription(product.getDescription());
        original.setCategory(product.getCategory());
        return original;
    }
    public Product persit(Product product){
        Product clone = product;
        clone.setId(++autoIncrementId);
        products.add(clone);
        return clone;
    }

    @Override
    public void delete(int idProduct) {
        products.removeIf(product -> product.getId() == idProduct);
    }

    @Override
    public void delete(Product product) {
        delete(product.getId());
    }

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public Category getCategory(int id) {
        Category category = null;
        for (Category c : categories) {
            if (c.getId() == id){
                category = c;
            }
        }
        return category;
    }
}
