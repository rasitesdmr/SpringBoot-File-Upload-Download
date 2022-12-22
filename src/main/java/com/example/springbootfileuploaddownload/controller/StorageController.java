package com.example.springbootfileuploaddownload.controller;

import com.example.springbootfileuploaddownload.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class StorageController {

    @Autowired
    StorageService storageService;

    @PostMapping()
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String uploadImage = storageService.uploadImage(multipartFile);
        return new ResponseEntity<>(uploadImage, HttpStatus.OK);
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<?> downloadImage(@PathVariable String firstName) {
        byte[] imageData = storageService.downloadImage(firstName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
    }
}
