package com.mycompany.production.optimizer.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mycompany.production.optimizer.backend.config.DatabaseConnection;
import java.sql.Connection;

@SpringBootApplication
public class ProductionOptimizerBackendApplication {
    public static void main(String[] args){
        SpringApplication.run(ProductionOptimizerBackendApplication.class, args);
        
        Connection conn = DatabaseConnection.getConnection();
        
        if(conn != null){
            System.out.println("Database Connection OK");
        }else{
            System.out.println("Database Connection Failed");
        }
    }
}
