package com.projects.ecommerce.helper;

import com.projects.ecommerce.entity.ImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public  class MultiPartFileProcessor {

    public static Set<ImageModel> uploadImage(MultipartFile[] files) throws IOException {
        Set<ImageModel> imageModelSet = new HashSet<>();

        for(MultipartFile file : files){
            ImageModel imageModel = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            imageModelSet.add(imageModel);
        }
        return imageModelSet;
    }
}
