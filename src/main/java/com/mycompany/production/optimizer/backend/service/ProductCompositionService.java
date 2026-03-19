package com.mycompany.production.optimizer.backend.service;

import com.mycompany.production.optimizer.backend.model.ProductComposition;
import com.mycompany.production.optimizer.backend.repository.ProductCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCompositionService {
    
     @Autowired
    private ProductCompositionRepository repository;

    public List<ProductComposition> getAllCompositions(){
        return repository.findAll();
    }

    public Optional<ProductComposition> getCompositionById(Long id){
        return repository.findById(id);
    }

    public ProductComposition saveComposition(ProductComposition composition){
        return repository.save(composition);
    }

    public void deleteComposition(Long id){
        repository.deleteById(id);
    }
    
}
