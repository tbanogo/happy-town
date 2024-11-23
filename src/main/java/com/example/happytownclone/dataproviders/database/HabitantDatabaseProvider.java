package com.example.happytownclone.dataproviders.database;

import com.example.happytownclone.core.entities.Habitant;
import com.example.happytownclone.core.use_cases.HabitantProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class HabitantDatabaseProvider implements HabitantProvider {

    private final HabitantJpaRepository habitantJpaRepository;

    public HabitantDatabaseProvider(HabitantJpaRepository habitantJpaRepository) {
        this.habitantJpaRepository = habitantJpaRepository;
    }

    @Override
    public List<Habitant> getAll() {
        return habitantJpaRepository.findAll()
                .stream()
                .map(this::toHabitant)
                .collect(toList());
    }

    @Override
    public List<Habitant> getEligiblesCadeaux(LocalDate dateArriveeCommune) {
        return habitantJpaRepository.findByDateArriveeCommuneLessThanEqualAndCadeauOffertIsNullAndDateAttributionCadeauIsNullOrderByDateArriveeCommune(dateArriveeCommune)
                .stream()
                .map(this::toHabitant)
                .collect(toList());
    }

    @Override
    public void save(Habitant habitant) {
        habitantJpaRepository.save(toHabitantJpa(habitant));
    }

    private HabitantJpa toHabitantJpa(Habitant habitant) {
        HabitantJpa habitantJpa = new HabitantJpa();
        habitantJpa.setId(habitant.getId());
        habitantJpa.setNom(habitant.getNom());
        habitantJpa.setPrenom(habitant.getPrenom());
        habitantJpa.setEmail(habitant.getEmail());
        habitantJpa.setDateNaissance(habitant.getDateNaissance());
        habitantJpa.setDateArriveeCommune(habitant.getDateArriveeCommune());
        habitantJpa.setAdressePostale(habitant.getAdressePostale());
        habitantJpa.setCadeauOffert(habitant.getCadeauOffert());
        habitantJpa.setDateAttributionCadeau(habitant.getDateAttributionCadeau());
        return habitantJpa;
    }

    private Habitant toHabitant(HabitantJpa habitantJpa) {
        Habitant habitant = new Habitant(
                habitantJpa.getId(),
                habitantJpa.getNom(),
                habitantJpa.getPrenom(),
                habitantJpa.getEmail(),
                habitantJpa.getDateNaissance(),
                habitantJpa.getDateArriveeCommune(),
                habitantJpa.getAdressePostale());
        habitant.attribuerCadeau(habitantJpa.getCadeauOffert(), habitantJpa.getDateAttributionCadeau());
        return habitant;
    }
}
