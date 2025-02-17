package com.multi.modular.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.modular.service.ModuleService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @GetMapping("/retrieve/all")
    public ResponseEntity<?> getAllModules() {
        return ResponseEntity.ok(moduleService.obtainAll());
    }

    // DOCU: Así como en los profesores se prefiere no enseñar su Id interno, en los módulos no supone información sensible
    @GetMapping("/retrieve/{id}")
    public ResponseEntity<?> getModule(@PathVariable Long id) {
        Module module = moduleService.obtainModuleById(id);
        return ResponseEntity.ok(module);
    }
    

    @PostMapping("/new")
    public ResponseEntity<?> createModule(@Valid @RequestBody Module moduleData) {
        Module addedModule = moduleService.add(moduleData); 
        return ResponseEntity.status(HttpStatus.CREATED).body(addedModule);
    }
    

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editModule(@PathVariable Long id, @Valid @RequestBody Module moduleData) {
        moduleService.obtainModuleById(id); 
        Module editedModule = moduleService.edit(moduleData);        
        return ResponseEntity.ok(editedModule);
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable Long id){
        moduleService.obtainModuleById(id);
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
}
