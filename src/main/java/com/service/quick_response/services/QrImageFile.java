package com.service.quick_response.services;

import com.service.quick_response.repositories.QrGenerateRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public abstract class QrImageFile implements QrGenerateRepository {
    @Override
    public String qrCodeImagePath(String imageName) {
        String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/%s.png";
        return String.format(QR_CODE_IMAGE_PATH, imageName != null ? imageName : "QRCode");
    }

    @Override
    public byte[] getQRFileGenerated(String fileName) throws IOException {
        Path imagePath = Paths.get(this.qrCodeImagePath(fileName));
        return Files.readAllBytes(imagePath);
    }
}
