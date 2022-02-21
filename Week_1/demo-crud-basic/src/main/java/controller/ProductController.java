package controller;

import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.IProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping("")
    public ModelAndView showProducts(){
        ModelAndView modelAndView = new ModelAndView("view");
        List<Product> products = productService.getAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("view");
        productService.delete(productService.getProduct(id));
        List<Product> products = productService.getAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    @GetMapping("/{id}/view-detail")
    public ModelAndView viewProductDetail(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.getProduct(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @GetMapping("/{id}/edit")
    public ModelAndView editProduct(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        List<Category> categories = productService.getAllCategories();
        Product product = productService.getProduct(id);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createProduct(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("edit");
        List<Category> categories = productService.getAllCategories();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
    @GetMapping("")
    public ModelAndView saveProduct(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("view" );
        productService.save(product);
        List<Product> products = productService.getAll();
        modelAndView.addObject("products", products);
        return modelAndView;
    }

}
