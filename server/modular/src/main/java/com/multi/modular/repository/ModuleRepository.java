package com.multi.modular.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>  {
    
}