package com.multi.modular.service.utils;

import org.springframework.web.multipart.MultipartFile;
    
import java.io.IOException;

import org.springframework.core.io.Resource;

public interface FileManagementService {
    String store(MultipartFile file, String folder) throws IOException;
    void delete(String fileName, String folder);
    Resource loadResource(String fileName, String folder);
} 
