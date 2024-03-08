package com.jaroso.plantaciones.service;

import com.jaroso.plantaciones.entity.Plantacion;
import com.jaroso.plantaciones.entity.Registro;
import com.jaroso.plantaciones.repository.PlantacionRepository;
import com.jaroso.plantaciones.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroService {

    @Autowired
    private final RegistroRepository registroRepo;

    public RegistroService(RegistroRepository registroRepository) {
        this.registroRepo = registroRepository;
    }

    //crud
    public List<Registro> findAll() {
        return this.registroRepo.findAll();
    }

    public Registro save(Registro registro) {
        return this.registroRepo.save(registro);
    };

    public Optional<Registro> findByid(Long id) {
        return this.registroRepo.findById(id);
    }

    public void deleteByid(Long id){
        this.registroRepo.deleteById(id);
    }
}
