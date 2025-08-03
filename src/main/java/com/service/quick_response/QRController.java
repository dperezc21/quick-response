package com.service.quick_response;

import com.service.quick_response.models.QuickResponseModel;
import com.service.quick_response.services.QRCodeGenerator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qr")
public class QRController {

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateQr(@RequestBody QuickResponseModel quickResponseModel) {
        try {
            QRContext qrContext = new QRContext(new QRCodeGenerator());
            qrContext.runStrategy(quickResponseModel.getMessage(), quickResponseModel.getImageName());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrContext.getQRFileGenerated());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
