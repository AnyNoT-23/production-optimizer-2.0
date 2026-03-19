package com.mycompany.production.optimizer.backend.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod")
    private Long id;

    @Column(name = "name_prod", nullable = false)
    private String name;

    @Column(name = "price_prod", nullable = false)
    private BigDecimal price;

    @Column(name = "createdat_prod", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedat_prod")
    private LocalDateTime updatedAt;

    public Product(){}

    public Product(Long id, String name, BigDecimal price, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public BigDecimal getPrice(){ return price; }
    public void setPrice(BigDecimal price){ this.price = price; }

    public LocalDateTime getCreatedAt(){ return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt(){ return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt){ this.updatedAt = updatedAt; }
    
}
