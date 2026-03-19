package com.mycompany.production.optimizer.backend.repository;

import com.mycompany.production.optimizer.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{
    
}
