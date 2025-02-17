package com.multi.modular.service.utils;

import org.springframework.web.multipart.MultipartFile;
    
import java.io.IOException;

import org.springframework.core.io.Resource;

public interface FileStorageService {
    String store(MultipartFile file) throws IOException;
    void delete(String fileName);
    Resource loadResource(String fileName);
} 
