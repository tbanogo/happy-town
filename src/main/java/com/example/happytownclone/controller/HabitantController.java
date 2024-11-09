package com.example.happytownclone.controller;

import com.example.happytownclone.domain.Habitant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/habitants")
@Schema(name = "API de gestion des habitants de Happy Town")
public class HabitantController {

    private final HabitantService habitantService;

    public HabitantController(HabitantService habitantService) {
        this.habitantService = habitantService;
    }

    @GetMapping
    @Operation(description = "Retourne la liste des habitants de Happy Town")
    public List<Habitant> getAllHabitants() {
        return this.habitantService.getAll();
    }
}
