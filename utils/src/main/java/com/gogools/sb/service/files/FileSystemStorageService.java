package com.gogools.sb.service.files;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gogools.enums.Emj;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	@PostConstruct
	public void init() {
		
		try {
			
            Files.createDirectories(rootLocation);
            log.info(Emj.INFO + " [FileSystemStorageService] Dir created: {}", rootLocation);
            
        } catch (IOException e) {
        	
            throw new StorageException("Could not initialize storage location", e);
        }
	}

	@Override
	public String store(MultipartFile file) {
		
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			
			if (file.isEmpty()) {
				
				log.info(Emj.ERR + " [FileSystemStorageService] file: {}, Failed to store empty file", filename);
				throw new StorageException("Failed to store empty file " + filename);
			}
			
			if (filename.contains("..")) {

				log.info(Emj.ERR + " [FileSystemStorageService] file: {}, Cannot store file with relative path outside current directory", filename);
				throw new StorageException("Cannot store file with relative path outside current directory " + filename);
			}
			
			try (InputStream inputStream = file.getInputStream()) {
				
				Files.copy(inputStream, 
							this.rootLocation.resolve(filename), 
							StandardCopyOption.REPLACE_EXISTING);
				
				log.info(Emj.INFO + " [FileSystemStorageService] file: {}, stored", filename);
			}
			
		} catch (IOException e) {
			
			throw new StorageException("Failed to store file " + filename, e);
		}

		return filename;
	}

	@Override
	public Stream<Path> loadAll() {
		
		try {
			
            return Files.walk(this.rootLocation, 1)
			                    .filter(path -> !path.equals(this.rootLocation))
			                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
        	
            throw new StorageException("Failed to read stored files", e);
        }
	}

	@Override
	public Path load(String filename) {
		
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		
		try {
			
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            
            if (resource.exists() || resource.isReadable()) {
            	
                return resource;
            }
            else {
            	
                throw new FileNotFoundException(
                        "Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
        	
            throw new FileNotFoundException("Could not read file: " + filename, e);
        }
	}

	@Override
	public void deleteAll() {

		log.info(Emj.INFO + " Deleting all files in {}", rootLocation);
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void deleteFile(String filename) {
		
		if(filename == null || filename.isEmpty()) return ;
		
		Path path = load(filename);
		
		log.info(Emj.INFO + " Deleting file: {}", path.getFileName());
		FileSystemUtils.deleteRecursively( path.toFile() );
	}

}
