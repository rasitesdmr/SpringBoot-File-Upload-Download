package com.example.springbootfileuploaddownload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    String uploadImage(MultipartFile multipartFile) throws IOException;

    byte[] downloadImage(String fileName);

}

