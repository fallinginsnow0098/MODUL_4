package controller;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.IService;
import service._IMP.ProductService;

import java.util.List;

@Controller
@EnableWebMvc
@RequestMapping("/product")
public class ProductController {
    private final IService productService = new ProductService();

    @GetMapping
    public ModelAndView displayAll(){
        ModelAndView mav = new ModelAndView("index");
        List<Product> products = productService.findAll();
        mav.addObject("products", products);
        return mav;
    }
    @GetMapping("{id}/view")
    public ModelAndView viewDetail(@PathVariable int id){
        ModelAndView mav = new ModelAndView("view");
        Product product = productService.findById(id);
        mav.addObject("product", product);
        return mav;
    }
    @GetMapping("{id}/delete")
    public ModelAndView delete(@PathVariable int id){
        ModelAndView mav = new ModelAndView("index");
        productService.remove(id);
        List<Product> products = productService.findAll();
        mav.addObject("products", products);
        return mav;
    }
    @PostMapping("create")
    public ModelAndView create(){
        ModelAndView mav = new ModelAndView("create");
        Product product = new Product();
        mav.addObject("product", product);
        return mav;
    }
    @PostMapping
    public ModelAndView createProduct(@ModelAttribute Product product){
        ModelAndView mav = new ModelAndView("index");
        if (productService.checkProduct(product.getId())){
            productService.save(product);
        } else {
            product.setId(productService.findAll().size() + 1);
            productService.save(product);
        }
        List<Product> products = productService.findAll();
        mav.addObject("products", products);
        return mav;
    }
    @GetMapping("{id}/edit")
    public ModelAndView editProduct(@PathVariable int id){
        ModelAndView mav = new ModelAndView("create");
        Product product = productService.findById(id);
        mav.addObject("product", product);
        return mav;
    }
    @PostMapping("search")
    public ModelAndView searchProduct(@RequestParam(name = "name", required = false) String name){
        ModelAndView mav = new ModelAndView("view");
        Product product = productService.search(name);
        mav.addObject("product", product);
        return mav;
    }
}
