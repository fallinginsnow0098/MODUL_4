package fallinginsnow.controller;

import fallinginsnow.model.Category;
import fallinginsnow.model.Product;
import fallinginsnow.service.ICategoryService;
import fallinginsnow.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/product")
public class HomeController {
    @Value("${file-upload}")
    private String fileUpload;

    @Value("${view}")
    private String view;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ModelAndView showProducts(){
        ModelAndView mav = new ModelAndView("list");
        ArrayList<Product> products = productService.getAllProduct();
        if (products.isEmpty()){
            mav.addObject("message", "No products");
            mav.addObject("color", "red");
        }
        mav.addObject("file", view);
        mav.addObject("products", products);
        return mav;
    }
    @GetMapping("delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView("list");
        Product product = productService.getProduct(id);
        if (product != null){
            productService.deleteProduct(product);
        } else {
            mav.addObject("message", "IN VALID");
            mav.addObject("color", "red");
        }
        ArrayList<Product> products = productService.getAllProduct();
        mav.addObject("products", products);
        return mav;
    }
    @GetMapping("/create")
    public ModelAndView createProduct(Model model){
        ModelAndView mav = new ModelAndView("create");
        ArrayList<Category> categories = categoryService.getAllCategory();
        mav.addObject("product", new Product());
        mav.addObject("categories", categories);
        return mav;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView("edit");
        Product product = productService.getProduct(id);
        if (product != null){
            mav.addObject("product", product);
        } else {
            mav.addObject("message", "IN VALID");
        }
        ArrayList<Category> categories = categoryService.getAllCategory();
        mav.addObject("categories", categories);
        return mav;
    }
    @GetMapping("view/{id}")
    public ModelAndView showDetail(@PathVariable("id") int id){
        ModelAndView mav = new ModelAndView("detail");
        Product product = productService.getProduct(id);
        if (product != null){
            mav.addObject("product", product);
        } else {
            mav.addObject("message", "IN VALID");
        }
        return mav;
    }
    @PostMapping("/test")
    public ModelAndView create(@ModelAttribute Product product){
        ModelAndView mav = new ModelAndView("list");
        Category category = categoryService.getCategory(Integer.parseInt(product.getCategory().getC_name()));
        product.setCategory(category);

        MultipartFile multipartFile = product.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(product.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message = productService.saveProduct(product);
        ArrayList<Product> products = productService.getAllProduct();
        mav.addObject("products", products);
        mav.addObject("message", message);
        return mav;
    }
}
