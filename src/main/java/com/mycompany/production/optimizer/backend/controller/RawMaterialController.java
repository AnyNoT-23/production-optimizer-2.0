package com.mycompany.production.optimizer.backend.controller;

import com.mycompany.production.optimizer.backend.model.RawMaterial;
import com.mycompany.production.optimizer.backend.service.RawMaterialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raw-materials")
public class RawMaterialController {
    
    @Autowired
    private RawMaterialService service;

    @GetMapping
    public List<RawMaterial> getAllRawMaterials(){
        return service.getAllRawMaterials();
    }

    @GetMapping("/{id}")
    public RawMaterial getRawMaterialById(@PathVariable Long id){
        return service.getRawMaterialById(id).orElse(null);
    }

    @PostMapping
    public RawMaterial createRawMaterial(@RequestBody RawMaterial rm){
        return service.saveRawMaterial(rm);
    }

    @PutMapping("/{id}")
    public RawMaterial updateRawMaterial(@PathVariable Long id, @RequestBody RawMaterial rm){
        rm.setId(id);
        return service.saveRawMaterial(rm);
    }

    @DeleteMapping("/{id}")
    public void deleteRawMaterial(@PathVariable Long id){
        service.deleteRawMaterial(id);
    }
    
}
