package com.mycompany.production.optimizer.backend.repository;

import com.mycompany.production.optimizer.backend.model.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long>{
    
}
