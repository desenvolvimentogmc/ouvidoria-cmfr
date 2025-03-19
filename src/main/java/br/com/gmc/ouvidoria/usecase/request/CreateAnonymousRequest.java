package br.com.gmc.ouvidoria.usecase.request;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.entity.model.Request;
import br.com.gmc.ouvidoria.enums.Status;
import br.com.gmc.ouvidoria.usecase.files.CreateFile;
import br.com.gmc.ouvidoria.utils.ProtocolUtils;

@Service
public class CreateAnonymousRequest {

    private final RequestGateway gateway;
    private final CreateFile createFile;

    public CreateAnonymousRequest(RequestGateway gateway, CreateFile createFile) {
        this.gateway = gateway;
        this.createFile = createFile;
    }
    
    public Request execute(Request request) {
        request.setRequester(null);
        request.setProtocol(ProtocolUtils.generate());
        request.setStatus(Status.OPEN);
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        return this.gateway.saveOrUpdate(request);
    }

    public Request execute(Request request, MultipartFile[] files) throws IOException {

        if(files ==null || files.length==0 || Objects.requireNonNull(files[0].getOriginalFilename()).isEmpty() || Objects.requireNonNull(files[0].getOriginalFilename()).isBlank()) {
            request.setAttachments(null);
            return this.execute(request);
        }

        this.createFile.execute(files).forEach(request::addAttachment);
        request.setRequester(null);
        request.setProtocol(ProtocolUtils.generate());
        request.setStatus(Status.OPEN);
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        return this.gateway.saveOrUpdate(request);
    }
    
}
