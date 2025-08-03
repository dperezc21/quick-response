package com.service.quick_response.repositories;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.IOException;

public interface QrGenerateRepository {
    default void generateQrCode(String message, String fileName) throws WriterException, IOException {}
    byte[] getQRFileGenerated(String fileName) throws IOException;
    String qrCodeImagePath(String fileName);
    default void deleteFile(String pathFile) {
        File file = new File(pathFile);
        if (file.delete()) System.out.println("File deleted successfully");
        else System.out.println("Failed to delete the file");
    }
}
