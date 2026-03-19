package com.mycompany.production.optimizer.backend.repository;

import com.mycompany.production.optimizer.backend.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long>{
    
}
