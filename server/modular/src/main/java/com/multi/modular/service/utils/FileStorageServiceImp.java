package com.multi.modular.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImp implements FileStorageService {

    private final Path rootLocation = Paths.get("uploadDir");

    public String store(MultipartFile file) throws IOException, RuntimeException {
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

    public Resource loadResource(String filename) {
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

    public void delete(String filename) throws RuntimeException {
        try {
            Path file = rootLocation.resolve(filename);
            if (!Files.exists(file))
                throw new RuntimeException("No existe el fichero");
            Files.delete(file);
        } catch (IOException ioe) {
            throw new RuntimeException("Error en borrado");
        }
    }

}
