package com.mycompany.production.optimizer.backend.repository;

import com.mycompany.production.optimizer.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
