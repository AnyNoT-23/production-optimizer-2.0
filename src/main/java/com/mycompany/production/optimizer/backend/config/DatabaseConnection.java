package com.mycompany.production.optimizer.backend.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/production-optimizer";
    private static final String USER = "postgres";
    private static final String PASSWORD = "202418";
    
    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Database connection succesfully!");
        }catch(SQLException e){
            System.out.println("Database connection failed: ");
            e.printStackTrace();
        } 
        return connection;
   }
}