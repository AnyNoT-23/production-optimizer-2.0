package com.mycompany.production.optimizer.backend.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "units")
public class Unit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unit")
    private Long id;

    @Column(name = "name_unit", nullable = false)
    private String name;

    @Column(name = "symbol_unit", nullable = false)
    private String symbol;

    @Column(name = "createdat_unit", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedat_unit")
    private LocalDateTime updatedAt;

    public Unit(){}

    public Unit(Long id, String name, String symbol, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.name = name;
        this.symbol = symbol;
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

    public String getSymbol(){ return symbol; }
    public void setSymbol(String symbol){ this.symbol = symbol; }

    public LocalDateTime getCreatedAt(){ return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt(){ return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt){ this.updatedAt = updatedAt; }
    
}
