package com.multi.modular.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org

@RestController
public class ModuleController {
    @Autowired
    private ModuleService moduleService;
}
