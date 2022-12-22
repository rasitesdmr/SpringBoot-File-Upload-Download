package com.example.springbootfileuploaddownload.service;

import com.example.springbootfileuploaddownload.model.ImageData;
import com.example.springbootfileuploaddownload.repository.StorageRepository;
import com.example.springbootfileuploaddownload.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageRepository storageRepository;

    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException {

        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .imageData(ImageUtils.compressImage(multipartFile.getBytes()))
                .build());
        if (imageData != null) {
            return "Dosya Başarıyla yüklendi :" + multipartFile.getOriginalFilename();
        }
        return null;
    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }


}
