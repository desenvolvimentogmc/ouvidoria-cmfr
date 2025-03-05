package br.com.gmc.ouvidoria.usecase.files;

import br.com.gmc.ouvidoria.entity.gateway.FileGateway;
import br.com.gmc.ouvidoria.entity.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see FileGateway
 * @see UploadFile
 */
@Service
public class CreateFile {

    private final FileGateway fileGateway;
    private final UploadFile uploadFile;

    public CreateFile(FileGateway fileGateway, UploadFile uploadFile) {
        this.fileGateway = fileGateway;
        this.uploadFile = uploadFile;
    }

    public List<File> execute(MultipartFile[] files) throws IOException {
        List<File> fileList = new ArrayList<>();

        Arrays.stream(files).forEach(file -> {
            try {
                var filePath = this.uploadFile.execute(file);
                fileList.add(this.fileGateway.saveOrUpdate(new File(filePath, file.getOriginalFilename())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        return fileList;
    }

}
