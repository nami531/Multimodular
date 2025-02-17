package com.multi.modular.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.multi.modular.repository.ModuleRepository;

public class ModuleServiceImpl implements ModuleService{

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public List<Module> obtainAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Module obtainModuleById(Long id) {
        return moduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No existe ningún módulo con el identificador"));
    }

    @Override
    public Module add(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public Module edit(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }
    
}
