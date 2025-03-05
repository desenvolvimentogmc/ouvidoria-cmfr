package br.com.gmc.ouvidoria.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@Component
public class FileUploadUtils {

    @Value("${spring.application.file.path}")
    private String storagePath;

    /**
     * escreve um arquivo no diretório do storagePath
     * @param file multipart
     * @return nome do arquivo salvo ou diretório/nome do arquivo salvo
     * @throws IOException IO exception, diretório não encontrado exception
     */
    public String write(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path filePath = Paths.get(storagePath, fileName);

        File directory = new File(storagePath);

        if (!directory.exists()) {
            throw new FileNotFoundException("Directory does not exist");
        }

        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

    /**
     * escreve um array de multipart files
     * @param files multipart[]
     * @return lista de caminhos/arquivos salvos
     * @throws IOException IO exception, diretório não encontrado exception
     */
    public List<String> writeMultiples(MultipartFile[] files) throws IOException {

        List<String> filesPath = new ArrayList<>();

        Arrays.stream(files).forEach(file -> {
            try {
                filesPath.add(write(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return filesPath;
    }
}
