package br.com.gmc.ouvidoria.infrastructure.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/files")
public class FileRestController {

    @Value("${spring.application.file.path}")
    private String storagePath;

    @GetMapping("/{imagePathOrName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imagePathOrName) throws IOException, MalformedURLException {

        Path path = Paths.get(storagePath + "/" + imagePathOrName);

        Resource resource = new UrlResource(path.toUri());

        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + path.getFileName().toString() + "\"");

        return ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA).headers(headers).body(resource);
    }

    @GetMapping("/public/{imagePathOrName}")
    public ResponseEntity<Resource> getPublicFile(@PathVariable String imagePathOrName) throws IOException, MalformedURLException {

        Path path = Paths.get(storagePath + "/assets/" + imagePathOrName);

        Resource resource = new UrlResource(path.toUri());

        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + path.getFileName().toString() + "\"");

        return ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA).headers(headers).body(resource);
    }
}
