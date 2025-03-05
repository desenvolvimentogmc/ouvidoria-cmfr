package br.com.gmc.ouvidoria.usecase.files;

import br.com.gmc.ouvidoria.utils.FileUploadUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see FileUploadUtils
 */
@Service
public class UploadFile {

    private final FileUploadUtils fileUploadUtils;

    public UploadFile(FileUploadUtils fileUploadUtils) {
        this.fileUploadUtils = fileUploadUtils;
    }

    public String execute(MultipartFile file) throws IOException {
        return this.fileUploadUtils.write(file);
    }

    public List<String> execute(MultipartFile[] files) throws IOException {
        return this.fileUploadUtils.writeMultiples(files);
    }
}
