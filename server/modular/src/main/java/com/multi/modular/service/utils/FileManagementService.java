package com.multi.modular.service.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileManagementService {
    String store(MultipartFile file, String folder) throws RuntimeException;
    void delete(String fileName, String folder);
    Resource loadResource(String fileName, String folder);
    List<String[]> readCsv(MultipartFile file) throws IOException; 
} 
