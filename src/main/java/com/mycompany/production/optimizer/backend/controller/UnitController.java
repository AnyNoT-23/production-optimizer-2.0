package com.mycompany.production.optimizer.backend.controller;

import com.mycompany.production.optimizer.backend.model.Unit;
import com.mycompany.production.optimizer.backend.service.UnitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController {
    
    @Autowired
    private UnitService service;

    @GetMapping
    public List<Unit> getAllUnits(){
        return service.getAllUnits();
    }

    @GetMapping("/{id}")
    public Unit getUnitById(@PathVariable Long id){
        return service.getUnitById(id).orElse(null);
    }

    @PostMapping
    public Unit createUnit(@RequestBody Unit unit){
        return service.saveUnit(unit);
    }

    @PutMapping("/{id}")
    public Unit updateUnit(@PathVariable Long id, @RequestBody Unit unit){
        unit.setId(id);
        return service.saveUnit(unit);
    }

    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable Long id){
        service.deleteUnit(id);
    }
    
}
