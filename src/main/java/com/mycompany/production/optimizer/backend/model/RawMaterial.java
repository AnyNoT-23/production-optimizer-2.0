package com.mycompany.production.optimizer.backend.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "raw_materials")
public class RawMaterial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rm")
    private Long id;

    @Column(name = "name_rm", nullable = false)
    private String name;

    @Column(name = "stockquantity_rm", nullable = false)
    private BigDecimal stockQuantity;

    @ManyToOne
    @JoinColumn(name = "id_unit_rm")
    private Unit unit;

    @Column(name = "createdat_rm", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedat_rm")
    private LocalDateTime updatedAt;

    public RawMaterial(){}

    public RawMaterial(Long id, String name, BigDecimal stockQuantity, Unit unit, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.unit = unit;
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

    public BigDecimal getStockQuantity(){ return stockQuantity; }
    public void setStockQuantity(BigDecimal stockQuantity){ this.stockQuantity = stockQuantity; }

    public Unit getUnit(){ return unit; }
    public void setUnit(Unit unit){ this.unit = unit; }

    public LocalDateTime getCreatedAt(){ return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt(){ return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt){ this.updatedAt = updatedAt; }
    
}
