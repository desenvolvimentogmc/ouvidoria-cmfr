package br.com.gmc.ouvidoria.usecase.request;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.entity.model.Request;
import br.com.gmc.ouvidoria.entity.model.User;
import br.com.gmc.ouvidoria.enums.Status;
import br.com.gmc.ouvidoria.usecase.files.CreateFile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestGateway
 * @see CreateFile
 */
@Service
public class CreateRequest {

    private final RequestGateway gateway;
    private final CreateFile createFile;

    public CreateRequest(RequestGateway gateway, CreateFile createFile) {
        this.gateway = gateway;
        this.createFile = createFile;
    }

    /**
     * @param newRequest requisição a ser salva
     * @return requisição salva
     */
    public Request execute(Request newRequest) {
        builder(newRequest);
        return this.gateway.saveOrUpdate(newRequest);
    }

    /**
     *
     * @param newRequest requisição a ser salva
     * @param files arquivos
     * @return requisição salva
     * @throws IOException caso o arquivo não seja salvo por algum motivo
     */
    public Request execute(Request newRequest, MultipartFile[] files) throws IOException {

        if(files ==null || files.length==0 || Objects.requireNonNull(files[0].getOriginalFilename()).isEmpty() || Objects.requireNonNull(files[0].getOriginalFilename()).isBlank()) {
            newRequest.setAttachments(null);
            return this.execute(newRequest);
        }

        builder(newRequest);
        this.createFile.execute(files).forEach(newRequest::addAttachment);
        return this.gateway.saveOrUpdate(newRequest);
    }

    /**
     * Constroi o objeto, preenchendo campos que são automatizados pelo sistema
     * @param newRequest requisição
     */
    private void builder(Request newRequest) {
        var loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        newRequest.setCreatedAt(LocalDateTime.now());
        newRequest.setUpdatedAt(LocalDateTime.now());
        newRequest.setRequester(new User(loggedUsername));
        newRequest.setStatus(Status.OPEN);
    }
}
