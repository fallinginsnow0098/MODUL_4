package service._IMP;

import model.Product;
import service.IService;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService {
    private static final ArrayList<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product(1, "A", 123.4, "Laptop"));
        products.add(new Product(2, "B", 123.4, "Phone"));
        products.add(new Product(3, "C", 123.4, "Tablet"));
        products.add(new Product(4, "D", 123.4, "Cameras"));
        products.add(new Product(5, "E", 123.4, "HeadPhone"));
    }


    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        for (Product p : products) {
            if (p.getId() == id){
                product = p;
            }
        }
        return product;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void update(Product product, int id) {
        int index = 0;
        for (int i = 0; i < products.size(); i++) {
            if (product.getId() == id){
                index = i;
            }
        }
        products.set(index, product);
    }

    @Override
    public void remove(int id) {
        products.removeIf(product -> product.getId() == id);
    }
    public boolean checkProduct(int id){
        for (Product p : products) {
            if (p.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public Product search(String name) {
        Product product = null;
        for (Product p : products) {
            if (p.getName().equals(name)){
                product = p;
                break;
            }
        }
        return product;
    }
}
