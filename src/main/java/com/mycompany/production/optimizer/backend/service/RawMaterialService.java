package com.mycompany.production.optimizer.backend.service;


import com.mycompany.production.optimizer.backend.model.RawMaterial;
import com.mycompany.production.optimizer.backend.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RawMaterialService {
    
    @Autowired
    private RawMaterialRepository repository;

    public List<RawMaterial> getAllRawMaterials(){
        return repository.findAll();
    }

    public Optional<RawMaterial> getRawMaterialById(Long id){
        return repository.findById(id);
    }

    public RawMaterial saveRawMaterial(RawMaterial rawMaterial){
        return repository.save(rawMaterial);
    }

    public void deleteRawMaterial(Long id){
        repository.deleteById(id);
    }
    
}
