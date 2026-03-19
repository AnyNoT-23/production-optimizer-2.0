package com.mycompany.production.optimizer.backend.controller;

import com.mycompany.production.optimizer.backend.model.Product;
import com.mycompany.production.optimizer.backend.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return service.getProductById(id).orElse(null);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        return service.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
    }
    
}
