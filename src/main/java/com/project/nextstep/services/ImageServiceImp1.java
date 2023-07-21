package com.project.nextstep.services;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/*
    This class manages Images of Suppliers Products.
    The File structure as follow:-
        Folder[serverPath] ==> Folder[SupplierEmail] ==> List<Folder[productId]>  ==>  List<Image[RandomImageName]>
 */

@Service
public class ImageServiceImp1 implements ImageService{

    private Environment env;

    @Autowired
    public ImageServiceImp1(Environment env) {
        this.env = env;
    }

    /*
    this method save an Image of a specific Product on the File System as Follow:-
        1- generate unique Name for the file
        2- get location path from app Properties file
        3- if  user and product directories not exist create them else save it
     */
    public String uploadImage(MultipartFile image, String userFolderName,String productFolderName){

        String imagePath = null;
        try {
        String fileName = UUID.randomUUID().toString() + "." + image.getOriginalFilename().split("\\.")[1];
        String fileSaveLocation = env.getProperty("image.upload.path");
        File destDir;
        if(productFolderName != null) {
            destDir = new File(fileSaveLocation + userFolderName + "\\" + productFolderName);
        }
        else {
            destDir = new File(fileSaveLocation + userFolderName);
        }
            if(!destDir.exists()){
                destDir.mkdirs();
            }
            imagePath = destDir.getPath().concat("\\"+fileName);
            image.transferTo(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return imagePath;

    }

    @Override
    public byte[] getImagebyte(String urlPath) {
        Path imagePath = Path.of(urlPath);
        Resource imageResource = new FileSystemResource(imagePath);
        try {
            return imageResource.getInputStream().readAllBytes();
        } catch (IOException e) {
            System.out.println("error getting image byte [] ");
            e.printStackTrace();
        }
        return null;
    }

    /*
    This method to delete all products images of a specific Supplier
     */
    public boolean deleteFolder(String folderName){
        Path directory = Path.of(env.getProperty("image.upload.path") + "\\" + folderName);
        try {
            if(!directory.toFile().exists())
                return true;
            Files.walk(directory)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(o -> o.delete());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<byte[]> getImages(List<String> urls) {

        List<byte[]> images = new ArrayList<>();
        try {
            for (String resourcePath : urls) {
                byte[] imageData = new FileSystemResource(resourcePath).getContentAsByteArray();
                images.add(imageData);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return images;
    }

    public Resource getImage(String url) {
        Path imagePath = Path.of(url);
        Resource imageResource = new FileSystemResource(imagePath);
        return imageResource;
    }



}
