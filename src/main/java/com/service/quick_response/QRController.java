package com.service.quick_response;

import com.service.quick_response.services.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qr")
public class QRController {
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";
    @Autowired private QRCodeGenerator qrCodeGenerator;

    @GetMapping("/generate/{text}")
    public ResponseEntity generateQr(@PathVariable String text) {
        try {
            qrCodeGenerator.generateQrCode(text, 300, 300, QR_CODE_IMAGE_PATH);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok("QR generated");
    }
}
