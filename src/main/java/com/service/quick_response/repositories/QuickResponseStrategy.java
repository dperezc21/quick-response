package com.service.quick_response.repositories;

import com.google.zxing.WriterException;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public abstract class QuickResponseStrategy {
    @Getter
    @Setter
    private String ImageName;

    public void generateQrCode(String message, String imageName) throws WriterException, IOException{
        setImageName(imageName);
    }

    public String qrCodeImagePath() {
        String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/%s.png";
        return String.format(QR_CODE_IMAGE_PATH, getImageName() != null ? getImageName() : "QRCode");
    }

}
