package com.multi.modular.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.multi.modular.domain.Folder;
import com.multi.modular.domain.Teacher;
import com.multi.modular.service.TeacherService;
import com.multi.modular.service.utils.FileManagementService;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @Autowired
    private FileManagementService fileManagementService;
    private final Folder FOLDER_NAME = Folder.TEACHER; 

    @GetMapping("/")
    public ResponseEntity<?> getAllTeachers(){
        List<Teacher> teachers = service.getAllTeachers(); 
        return ResponseEntity.ok(teachers);
    }    
    
    // I don't like specifying that it throws an exception in an endpoint. But IOException is a checked 
    // exception, so we need to declare it
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> newElement(@RequestPart("file") MultipartFile file) throws IOException {
        List<String[]> values = fileManagementService.readCsv(file); 
        return ResponseEntity.status(HttpStatus.CREATED).body(values);
    }

    
}
