package fallinginsnow.service._Implement;

import fallinginsnow.model.Product;
import fallinginsnow.service.IProductService;

import java.util.ArrayList;

public class ProductService implements IProductService {
    private static final ArrayList<Product> products = new ArrayList<>();
    private static int id_auto = 0;
    @Override
    public ArrayList<Product> getAllProduct() {
        return products;
    }

    @Override
    public String saveProduct(Product product) {
        if (product.getId() == 0) {
            return persist(product);
        } else {
            return merge(product);
        }
    }

    public String persist(Product product){
        product.setId(++id_auto);
        products.add(product);
        return "Create Success";
    }
    public String merge(Product product){
        int index = 0;
        for (Product p : products) {
            if (p.getId() == product.getId()){
                index = products.indexOf(p);
            }
        }
        products.set(index, product);
        return "Update Success";
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
    }

    @Override
    public Product getProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }
}
