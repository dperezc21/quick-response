package com.service.quick_response;

import com.google.zxing.WriterException;
import com.service.quick_response.repositories.QuickResponseStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QRContext {

    private final QuickResponseStrategy strategy;

    public QRContext(QuickResponseStrategy quickResponseStrategy) {
        this.strategy = quickResponseStrategy;
    }

    public void runStrategy(String message, String imageFile) throws IOException {
        try {
            strategy.generateQrCode(message, imageFile);
        } catch (WriterException e) {
            System.out.println();
        }
    }

    public byte[] getQRFileGenerated() throws IOException {
        Path imagePath = Paths.get(strategy.qrCodeImagePath());
        return Files.readAllBytes(imagePath);
    }
}
