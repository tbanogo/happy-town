package com.example.happytownclone.core.use_cases;

import com.example.happytownclone.domain.Habitant;

import java.time.LocalDate;
import java.util.List;

public interface HabitantProvider {

    List<Habitant> getAll();

    List<Habitant> getEligiblesCadeaux(LocalDate dateArriveeCommune);

    void save(Habitant habitant);

}
