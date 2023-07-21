package com.project.nextstep.services;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public interface ImageService{

    List<byte[]> getImages(List<String> urls);

    Resource getImage( String url);

    boolean deleteFolder(String folderName);

    String uploadImage(MultipartFile image,String userFolderName, String productFolderName);

    byte[] getImagebyte(String urlPath);
}
