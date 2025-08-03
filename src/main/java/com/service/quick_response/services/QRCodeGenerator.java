package com.service.quick_response.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class QRCodeGenerator extends QrImageFile {

    @Override
    public void generateQrCode(String message, String imageName) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(message, BarcodeFormat.QR_CODE, 300, 300);
        Path path = FileSystems.getDefault().getPath(this.qrCodeImagePath(imageName));
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("File created successfully");
    }
}
