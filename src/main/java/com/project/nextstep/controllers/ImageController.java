package com.project.nextstep.controllers;

import com.project.nextstep.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile imageFile
    ) {
        String imagePath =  imageService.uploadImage(imageFile, "miscellaneous",null);
        return ResponseEntity.ok(imagePath.substring(30));
    }

    @GetMapping(value = "/{imagePath}")
    public ResponseEntity<Resource> serveImage(@PathVariable("imagePath") String imagePath){

        String urlPath = "C:\\ServerImages\\miscellaneous\\".concat(imagePath);

        Resource imageResource = imageService.getImage(urlPath);

        if (imageResource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccessControlAllowOrigin("*");
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .headers(headers)
                    .body(imageResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "bytes/{imagePath}")
//    @CrossOrigin("*")
    public ResponseEntity<byte[]> getByteImage(@PathVariable("imagePath") String imagePath){

        String urlPath = "C:\\ServerImages\\miscellaneous\\".concat(imagePath);

        byte[] imageResource = imageService.getImagebyte(urlPath);

        if (imageResource !=null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
