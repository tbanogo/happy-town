package com.example.happytownclone.service;

import com.example.happytownclone.domain.Habitant;
import com.example.happytownclone.repository.HabitantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class HabitantService {

    private final HabitantRepository habitantRepository;

    public HabitantService(HabitantRepository habitantRepository) {
        this.habitantRepository = habitantRepository;
    }

    public List<Habitant> getAll() {
        return this.habitantRepository.findAll();
    }
}
