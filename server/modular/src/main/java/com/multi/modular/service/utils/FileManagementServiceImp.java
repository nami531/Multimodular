package com.multi.modular.service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.multi.modular.exceptions.EmptyFile;

@Service
public class FileManagementServiceImp implements FileManagementService {

    private final Path rootLocation = Paths.get("uploads");

    public String store(MultipartFile file, String folder) throws RuntimeException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = StringUtils.getFilenameExtension(filename);
        String storedFilename = System.currentTimeMillis() + "." + extension;
        if (file.isEmpty())
            throw new RuntimeException("archivo enviado vacío");
        if (filename.contains(".."))
            throw new RuntimeException("nombre de archivo incorrecto");
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.rootLocation.resolve(storedFilename),
                    StandardCopyOption.REPLACE_EXISTING);
            return storedFilename;
        } catch (IOException ioe) {
            throw new RuntimeException("Error al almacenar el archivo");
        }
    }

    public Resource loadResource(String filename, String folder) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("Error IO");
        }
        return null;
    }

    public void delete(String filename, String folder) throws RuntimeException {
        try {
            Path file = rootLocation.resolve(filename);
            if (!Files.exists(file))
                throw new RuntimeException("No existe el fichero");
            Files.delete(file);
        } catch (IOException ioe) {
            throw new RuntimeException("Error en borrado");
        }
    }

    public List<String[]> readCsv(MultipartFile file) throws IOException{
        List<String[]> data = new ArrayList<>(); 
        
        if (file.isEmpty()){
            throw new EmptyFile(); 
        }
        Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8); 
        BufferedReader br = new BufferedReader(reader); 

        br.lines().forEach(line -> data.add(line.split("[;,]")));
        return data; 
    }

}
