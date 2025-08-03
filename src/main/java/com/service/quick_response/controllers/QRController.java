package com.service.quick_response.controllers;

import com.service.quick_response.services.QrContextStrategy;
import com.service.quick_response.models.QuickResponseModel;
import com.service.quick_response.repositories.QrGenerateRepository;
import com.service.quick_response.services.QRCodeGenerator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qr")
public class QRController {

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateQr(@RequestBody QuickResponseModel quickResponseModel) {
        QrContextStrategy qrContextStrategy = new QrContextStrategy(new QRCodeGenerator());
        QrGenerateRepository result = qrContextStrategy.getStrategy();
        String fileName = quickResponseModel.getImageName();
        try {
            result.generateQrCode(quickResponseModel.getMessage(), fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(result.getQRFileGenerated(fileName));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        } finally {
            result.deleteFile(result.qrCodeImagePath(fileName));
        }
    }
}
