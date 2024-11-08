package com.example.happytownclone.controller;

import com.example.happytownclone.service.HappyTownService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/attributionCadeaux")
@Schema(name = "API permettant d'attribuer un cadeau aux habitants de Happy Town arrivés depuis plus d'un an")
public class HappyTownController {

    private final HappyTownService happyTownService;

    private static final String SMPTP_HOST = "localhost";
    private static final int SMPTP_PORT =  2525;

    public HappyTownController(HappyTownService happyTownService) {
        this.happyTownService = happyTownService;
    }

    @PostMapping
    @Operation(description = "Permet d'attribuer un cadeau aux habitants de Happy Town arrivés depuis plus d'un an")
    public void attribuerCadeaux() throws MessagingException, IOException {
        String fileName = "src/main/resources/cadeaux.txt";
        LocalDate now = LocalDate.now();
        happyTownService.attribuerCadeaux(fileName, now, SMPTP_HOST, SMPTP_PORT);
    }
}
