package com.example.totp

import com.google.zxing.BarcodeFormat.QR_CODE
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage

@Service
class QRCodeGenerator() {
    private val qrCodeWriter = QRCodeWriter()

    fun generate(issuer: String, email: String, secret: String): BufferedImage {
        val uri = "otpauth://totp/${issuer}:${email}?secret=${secret}&issuer=${issuer}"
        val matrix = qrCodeWriter.encode(uri, QR_CODE, 300, 300)

        return MatrixToImageWriter.toBufferedImage(matrix)
    }
}
