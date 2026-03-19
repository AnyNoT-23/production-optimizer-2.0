package com.mycompany.production.optimizer.backend.repository;

import com.mycompany.production.optimizer.backend.model.ProductComposition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCompositionRepository extends JpaRepository<ProductComposition, Long>{
    
}
