package com.example.happytownclone.core.use_cases;

import com.example.happytownclone.core.entities.Cadeau;
import com.example.happytownclone.core.entities.Habitant;
import com.example.happytownclone.core.entities.TrancheAge;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Component
public class AttribuerCadeaux {

    private final HabitantProvider habitantProvider;
    private final CadeauxByTrancheAgeProvider cadeauxByTrancheAgeProvider;
    private final NotificationProvider notificationProvider;
    private final CadeauRandom cadeauRandom;

    private Clock clock;

    public static final String PATH_TEMPLATE_MESSAGE_CADEAU = "src/main/resources/messageCadeau.txt";
    public static final String PATH_TEMPLATE_MESSAGE_RECAP_CADEAUX = "src/main/resources/messageRecapCadeaux.txt";

    public AttribuerCadeaux(HabitantProvider habitantProvider, CadeauxByTrancheAgeProvider cadeauxByTrancheAgeProvider, NotificationProvider notificationProvider, CadeauRandom cadeauRandom, Clock clock) {
        this.habitantProvider = habitantProvider;
        this.cadeauxByTrancheAgeProvider = cadeauxByTrancheAgeProvider;
        this.notificationProvider = notificationProvider;
        this.cadeauRandom = cadeauRandom;
        this.clock = clock;
    }

    public void execute() {
        LocalDate now = LocalDate.now(clock);

        Map<TrancheAge, List<Cadeau>> cadeauxByTrancheAge = cadeauxByTrancheAgeProvider.get();
        List<Habitant> habitantsEligibles = habitantProvider.getEligiblesCadeaux(now.minusYears(1));

        habitantsEligibles.forEach(
                habitant -> attributionCadeau(habitant, cadeauxByTrancheAge, now)
        );

        List<Habitant> habitantsAttributionCadeau = habitantsEligibles.stream()
                .filter(Habitant::hasCadeau)
                .collect(toList());

        habitantsAttributionCadeau.forEach(
                habitant -> {
                    envoiMessageCadeau(habitant);
                    habitantProvider.save(habitant);
                }
        );

        envoiMessageRecapCadeaux(habitantsAttributionCadeau, now);
    }

    private void envoiMessageRecapCadeaux(List<Habitant> habitantsAttributionCadeau, LocalDate now) {
        if (!habitantsAttributionCadeau.isEmpty()) {
            String to = "mairie+service-cadeau@happytown.com";
            String subject = String.format("%1$td/%1$tm/%1$tY", now) + " - Synthese des cadeaux pour envoi";
            String cadeaux = "";
            for (Habitant habitantAttributionCadeau : habitantsAttributionCadeau) {
                cadeaux += "<li>" + habitantAttributionCadeau.getPrenom() + " " + habitantAttributionCadeau.getNom() + " : " + habitantAttributionCadeau.getCadeauOffert() + "</li>";
            }
            Map<String, String> templateArgs = new HashMap<>();
            templateArgs.put("cadeaux", cadeaux);
            notificationProvider.notifier(to, subject, PATH_TEMPLATE_MESSAGE_RECAP_CADEAUX, templateArgs);
        }
    }

    private void envoiMessageCadeau(Habitant habitant) {
        String to = habitant.getEmail();
        String subject = "Happy Birthday in HappyTown!";
        Map<String, String> templateArgs = new HashMap<>();
        templateArgs.put("prenom", habitant.getPrenom());
        templateArgs.put("nom", habitant.getNom());
        templateArgs.put("cadeau", habitant.getCadeauOffert());
        notificationProvider.notifier(to, subject, PATH_TEMPLATE_MESSAGE_CADEAU, templateArgs);
    }

    private void attributionCadeau(Habitant habitant, Map<TrancheAge, List<Cadeau>> cadeauxByTrancheAge, LocalDate now) {
        Optional<TrancheAge> trancheAge = getTrancheAgeCadeauHabitant(now, habitant, cadeauxByTrancheAge.keySet());
        if (trancheAge.isPresent()) {
            List<Cadeau> cadeauxEligibles = cadeauxByTrancheAge.get(trancheAge.get());
            Cadeau cadeau = cadeauRandom.get(cadeauxEligibles);
            habitant.attribuerCadeau(cadeau.getDetail(), now);
        }
    }

    private Optional<TrancheAge> getTrancheAgeCadeauHabitant(LocalDate now, Habitant habitant, Set<TrancheAge> trancheAges) {
        Integer ageHabitant = Period.between(habitant.getDateNaissance(), now).getYears();
        return trancheAges.stream()
                .filter(trancheAge -> ageHabitant >= trancheAge.getAgeMin() &&
                        ageHabitant < trancheAge.getAgeMax())
                .findFirst();
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
