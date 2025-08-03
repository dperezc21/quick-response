package com.service.quick_response.controllers;

import com.service.quick_response.repositories.QrGenerateRepository;
import com.service.quick_response.services.QRCodeGenerator;
import com.service.quick_response.services.QrContextStrategy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qr")
public class QRController {

    @GetMapping("/generate/{message}")
    public ResponseEntity<byte[]> generateQr(@PathVariable String message, @RequestParam(required = false) String fileName) {
        QrContextStrategy qrContextStrategy = new QrContextStrategy(new QRCodeGenerator());
        QrGenerateRepository result = qrContextStrategy.getStrategy();
        try {
            result.generateQrCode(message, fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(result.getQRFileGenerated(fileName));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        } finally {
            result.deleteFile(result.qrCodeImagePath(fileName));
        }
    }
}
