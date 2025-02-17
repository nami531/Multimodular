package com.multi.modular.service;

import java.util.List;

public interface ModuleService{
    List<Module> obtainAll();
    Module obtainModuleById(Long id);
    Module add(Module module);
    Module edit(Module module);
    void deleteModule(Long id);
    
}
