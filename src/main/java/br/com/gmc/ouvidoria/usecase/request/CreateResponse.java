package br.com.gmc.ouvidoria.usecase.request;

import br.com.gmc.ouvidoria.entity.gateway.ResponseGateway;
import br.com.gmc.ouvidoria.entity.model.Request;
import br.com.gmc.ouvidoria.entity.model.Response;
import br.com.gmc.ouvidoria.entity.model.User;
import br.com.gmc.ouvidoria.usecase.files.CreateFile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see ResponseGateway
 * @see CreateFile
 */
@Service
public class CreateResponse {

    private final ResponseGateway gateway;
    private final CreateFile createFile;

    public CreateResponse(ResponseGateway gateway, CreateFile createFile) {
        this.gateway = gateway;
        this.createFile = createFile;
    }

    public Response execute(Response response, Request request) {
        response.setCreatedAt(LocalDateTime.now());
        response.setRequest(request);
        response.setUser(new User(SecurityContextHolder.getContext().getAuthentication().getName()));
        return response;
    }

    public Response execute(Response response, Request request, MultipartFile[] files) throws IOException {

        if(files == null || files.length == 0) return this.execute(response, request);

        response.setUser(new User(SecurityContextHolder.getContext().getAuthentication().getName()));
        response.setCreatedAt(LocalDateTime.now());
        response.setRequest(request);

        this.createFile.execute(files).forEach(file -> {
            System.out.println(file.getOriginalFileName());
            response.addAttachment(file);
        });

        return this.gateway.saveOrUpdate(response);
    }
}
