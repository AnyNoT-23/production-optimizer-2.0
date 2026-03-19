package com.mycompany.production.optimizer.backend.controller;

import com.mycompany.production.optimizer.backend.model.ProductComposition;
import com.mycompany.production.optimizer.backend.service.ProductCompositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-compositions")
public class ProductCompositionController {

    @Autowired
    private ProductCompositionService service;

    @GetMapping
    public List<ProductComposition> getAll(){
        return service.getAllCompositions();
    }

    @GetMapping("/{id}")
    public ProductComposition getById(@PathVariable Long id){
        return service.getCompositionById(id).orElse(null);
    }

    @PostMapping
    public ProductComposition create(@RequestBody ProductComposition pc){
        return service.saveComposition(pc);
    }

    @PutMapping("/{id}")
    public ProductComposition update(@PathVariable Long id, @RequestBody ProductComposition details){

        ProductComposition composition = service.getCompositionById(id).orElseThrow();

        composition.setProduct(details.getProduct());
        composition.setRawMaterial(details.getRawMaterial());
        composition.setRequiredQuantity(details.getRequiredQuantity());

        return service.saveComposition(composition);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteComposition(id);
    }
}