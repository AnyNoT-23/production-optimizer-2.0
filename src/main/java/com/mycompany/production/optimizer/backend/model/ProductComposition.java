package com.mycompany.production.optimizer.backend.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product_compositions")
public class ProductComposition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pc")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_prod_pc")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_rm_pc")
    private RawMaterial rawMaterial;

    @Column(name = "requiredquantity_pc")
    private BigDecimal requiredQuantity;

    public ProductComposition(){}

    public ProductComposition(Long id, Product product, RawMaterial rawMaterial, BigDecimal requiredQuantity){
        this.id = id;
        this.product = product;
        this.rawMaterial = rawMaterial;
        this.requiredQuantity = requiredQuantity;
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }

    public Product getProduct(){ return product; }
    public void setProduct(Product product){ this.product = product; }

    public RawMaterial getRawMaterial(){ return rawMaterial; }
    public void setRawMaterial(RawMaterial rawMaterial){ this.rawMaterial = rawMaterial; }

    public BigDecimal getRequiredQuantity(){ return requiredQuantity; }
    public void setRequiredQuantity(BigDecimal requiredQuantity){ this.requiredQuantity = requiredQuantity; }
    
}
