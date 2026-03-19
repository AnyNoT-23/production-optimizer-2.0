package com.mycompany.production.optimizer.backend.service;

import com.mycompany.production.optimizer.backend.model.Unit;
import com.mycompany.production.optimizer.backend.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    
    @Autowired
    private UnitRepository repository;

    public List<Unit> getAllUnits(){
        return repository.findAll();
    }

    public Optional<Unit> getUnitById(Long id){
        return repository.findById(id);
    }

    public Unit saveUnit(Unit unit){
        return repository.save(unit);
    }

    public void deleteUnit(Long id){
        repository.deleteById(id);
    }
    
}
