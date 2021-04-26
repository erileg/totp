package com.example.totp

import dev.turingcomplete.kotlinonetimepassword.GoogleAuthenticator
import dev.turingcomplete.kotlinonetimepassword.GoogleAuthenticator.Companion.createRandomSecret
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.BufferedImageHttpMessageConverter
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.util.*

@EnableScheduling
@SpringBootApplication
class TotpApplication {
    companion object {
        private val log = LoggerFactory.getLogger(TotpApplication::class.java)
    }

    private val secret = createRandomSecret()

    init {
        log.info(secret)
    }

    @Bean
    fun imageConverter() = BufferedImageHttpMessageConverter()

    @Scheduled(fixedRate = 1000L)
    fun logCurrentCode() {
        val timestamp = Date(System.currentTimeMillis())
        val code = GoogleAuthenticator(secret).generate(timestamp)

        log.info(code)
    }
}

fun main(args: Array<String>) {
    runApplication<TotpApplication>(*args)
}
