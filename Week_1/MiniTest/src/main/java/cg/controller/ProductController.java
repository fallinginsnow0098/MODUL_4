package cg.controller;

import cg.model.Product;
import cg.service.IProductService;
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
public class ProductController {
    @Autowired
    private IProductService productService;

    @Value("${file-upload}")
    private String fileUpload;
    @Value("${view}")
    private String view;
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
    @GetMapping("/search/")
    public ModelAndView search(@RequestParam("keyword") String keyword){
        ModelAndView mav = new ModelAndView("products");
        ArrayList<Product> products = productService.findByKeyword(keyword);
        if (products.isEmpty()){
            mav.addObject("message", "Keyword INVALID!");
        }
        mav.addObject("products", products);
        return mav;
    }


    @GetMapping("/create")
    public ModelAndView createProduct(@ModelAttribute Product product){
        ModelAndView mav = new ModelAndView("create");
        MultipartFile file = product.getFile();
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(product.getFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImageURL("image//" + fileName);
        Product createProduct =  productService.save(product);
        if (createProduct != null){
            mav.addObject("message", "Create success !!");
        }
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
    @PostMapping("/edit/{id}")
    public ModelAndView editProduct(@ModelAttribute Product product, @PathVariable int id){
        ModelAndView mav = new ModelAndView("edit");
        product.setId(id);
        if (product.getFile().getSize() != 0){
            MultipartFile multipartFile = product.getFile();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(product.getFile().getBytes(), new File(fileUpload + fileName));
            } catch (Exception e){
                e.printStackTrace();
            }
            product.setImageURL("image/" + fileName);
        } else {
            product.setImageURL(productService.findById(id).getImageURL());
        }
        Product editProduct = productService.save(product);
        if (editProduct != null){
            mav.addObject("message", "UPDATE SUCCESS!!");
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
