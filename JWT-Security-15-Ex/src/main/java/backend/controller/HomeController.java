package backend.controller;

import backend.model.Category;
import backend.model.Product;
import backend.service.ICategoryService;
import backend.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Controller
@RequestMapping("/home/")
public class HomeController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> showAll(){
        Iterable<Product> products= productService.findAll();
        if (!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
    @GetMapping("all-page")
    public ResponseEntity<Page<Product>> showAllPage(@PageableDefault(value = 10)Pageable pageable){
        Page<Product> products = productService.findPage(pageable);
        if (!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
    }
    @GetMapping("all-categories")
    public ResponseEntity<Iterable<Category>> showAllCategories(){
        Iterable<Category> categories = categoryService.findAll();
        if (!categories.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(categories,HttpStatus.OK);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Product> showProduct(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(product.get(),HttpStatus.OK);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        MultipartFile file = product.getFile();
        String filename = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(product.getFile().getBytes(), new File(fileUpload + filename));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Create file success!");
        }
        product.setImgUrl(fileUpload + filename);
        Product newProduct = productService.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<Product> editProduct(@RequestBody Product productEdit, @PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            productEdit.setId(product.get().getId());
            productEdit = productService.save(productEdit);
            return new ResponseEntity<>(productEdit, HttpStatus.OK);
        }
    }
    @GetMapping("{search}}")
    public ResponseEntity<Iterable<Product>> search(@PathVariable String search){
        Iterable<Product> products = productService.findByName(search);
        if (!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
    @GetMapping("category")
    public ResponseEntity<Iterable<Product>> searchCategory(@RequestParam Long idCategory){
        Iterable<Product> products = productService.findByCategoryId(idCategory);
        if (!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
}
