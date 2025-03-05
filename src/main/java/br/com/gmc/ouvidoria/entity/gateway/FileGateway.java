package br.com.gmc.ouvidoria.entity.gateway;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.gmc.ouvidoria.entity.model.File;
import br.com.gmc.ouvidoria.infrastructure.repository.FileRepository;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see File
 * @see FileRepository
 */
@Component
public class FileGateway {
	
	private final FileRepository repository;

	public FileGateway(FileRepository repository) {
		super();
		this.repository = repository;
	}
	
	/**
	 * 
	 * @param newFile File
	 * @return arquivo salvo
	 */
	public File saveOrUpdate(File newFile) {
		return this.repository.save(newFile);
	}
	
	/**
	 * 
	 * @return lista de arquivos
	 */
	public List<File> findAll() {
		return this.repository.findAll();
	}
	
	/**
	 * 
	 * @param uuid UUID
	 * @return arquivo ou nulo
	 */
	public File findById(UUID uuid) {
		return this.repository.findById(uuid).orElse(null);
	}

}
