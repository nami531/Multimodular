package com.multi.modular.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.multi.modular.domain.Folder;
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

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> newElement(@RequestPart("file") MultipartFile file) {
        List<String[]> values = new ArrayList<>(); 
        try {
            values = fileManagementService.readCsv(file); 
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(values);
    }
}
