package cg.controller;

import cg.model.Product;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ModelAndView showAllProducts(){
        ModelAndView mav = new ModelAndView("products");
        ArrayList<Product> products = productService.findAll();
        if (products.isEmpty()){
            mav.addObject("message", "No Products!");
        }
        mav.addObject("products", products);
        return mav;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView("products");
        Product product = productService.delete(id);
        if (product == null){
            mav.addObject("message", "ID INVALID!!");
        }
        ArrayList<Product> products = productService.findAll();
        mav.addObject("products", products);
        return mav;
    }
    @GetMapping("/view/{id}")
    public ModelAndView viewProductDetail(@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView("detail");
        Product product = productService.findById(id);
        if (product != null){
            mav.addObject("product", product);
        } else {
            mav.addObject("message", "ID INVALID!");
        }
        return mav;
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam(name = "keyword", required = false)String keyword){
        ModelAndView mav = new ModelAndView("products");
        ArrayList<Product> products = productService.findByKeyword(keyword);
        if (products.isEmpty()){
            mav.addObject("message", "Keyword INVALID!");
        }
        mav.addObject("products", products);
        return mav;
    }
    @GetMapping("/create")
    public ModelAndView createProduct(Model model){
        ModelAndView mav = new ModelAndView("create");
        mav.addObject("product", new Product());
        return mav;
    }
    @PostMapping
    public ModelAndView create(@ModelAttribute Product product){
        ModelAndView mav = new ModelAndView("create");
        Product saveProduct = productService.save(product);
        if (saveProduct != null){
            mav.addObject("message", "Create Success!!");
        } else {
            mav.addObject("message", "ID INVALID!");
        }
        return mav;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView("edit");
        Product product = productService.findById(id);
        if (product != null){
            mav.addObject("product", product);
        } else {
            mav.addObject("message", "ID INVALID!");
        }
        return mav;
    }
    @PostMapping("/{id}")
    public ModelAndView edit(@ModelAttribute Product product, @PathVariable("id") int id){
        ModelAndView mav = new ModelAndView("edit");
        product.setId(id);
        Product editProduct = productService.save(product);
        if (editProduct != null){
            mav.addObject("message", "Update Success!!");
        } else {
            mav.addObject("message", "ID INVALID!!");
        }
        return mav;
    }
}
