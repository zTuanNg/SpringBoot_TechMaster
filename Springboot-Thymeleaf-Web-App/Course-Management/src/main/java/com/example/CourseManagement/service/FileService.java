package com.example.CourseManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private final Path rootPath = Paths.get("uploads");

    public FileService(){
        createFolder(rootPath.toString());
    }

    // Create folder
    public void createFolder(String path) {
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
    }

    public void saveFile(String uploadDir
            , String fileName
            , MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex){
            throw new IOException("Could not save file: " + fileName, ex);
        }
    }

    public void cleanDir(String dir){
        Path  dirPath = Paths.get(dir);

        try{
            Files.list(dirPath).forEach(file -> {
                if(!Files.isDirectory(file)){
                    try{
                        Files.delete(file);
                    }catch (IOException ex){
                        System.out.println("Could not delete file: "+ file);
                    }

                }
            });
        }catch (IOException ex2){
            System.out.println("Could not list directory: "+dirPath);
        }
    }

    public String getRootPath(){
        return this.rootPath.toString();
    }


}