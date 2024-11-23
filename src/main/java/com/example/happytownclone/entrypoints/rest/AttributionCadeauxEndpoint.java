package com.example.happytownclone.entrypoints.rest;

import com.example.happytownclone.core.use_cases.AttribuerCadeaux;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attributionCadeaux")
@Schema(name = "API permettant d'attribuer un cadeau aux habitants de Happy Town arrivés depuis plus d'un an")
public class AttributionCadeauxEndpoint {

    private final AttribuerCadeaux attribuerCadeaux;

    public AttributionCadeauxEndpoint(AttribuerCadeaux attribuerCadeaux) {
        this.attribuerCadeaux = attribuerCadeaux;
    }

    @GetMapping
    @Operation(description = "Permet d'attribuer un cadeau aux habitants de Happy Town arrivés depuis plus d'un an")
    public void attribuerCadeaux() {
        attribuerCadeaux.execute();
    }
}
