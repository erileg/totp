package com.example.totp

import org.springframework.http.MediaType.IMAGE_PNG_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.awt.image.BufferedImage

@RestController
class TotpController(private val qrCodeGenerator: QRCodeGenerator) {
    @GetMapping("/totpCode/{secret}", produces = [IMAGE_PNG_VALUE])
    fun generateTotpCode(@PathVariable secret:String): BufferedImage {
        return qrCodeGenerator.generate("topt.example.com", "mail@example.com", secret)
    }
}


