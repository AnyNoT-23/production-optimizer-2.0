package com.mycompany.production.optimizer.backend.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String HOST = System.getenv("PGHOST") != null ? System.getenv("PGHOST") : "localhost";
    private static final String PORT = System.getenv("PGPORT") != null ? System.getenv("PGPORT") : "5432";
    private static final String DB = System.getenv("PGDATABASE") != null ? System.getenv("PGDATABASE") : "production-optimizer";
    private static final String USER = System.getenv("PGUSER") != null ? System.getenv("PGUSER") : "postgres";
    private static final String PASSWORD = System.getenv("PGPASSWORD") != null ? System.getenv("PGPASSWORD") : "202418";
    
    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB;
    
    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection succesfully!");
        }catch(SQLException e){
            System.out.println("Database connection failed: ");
            e.printStackTrace();
        } 
        return connection;
   }
}