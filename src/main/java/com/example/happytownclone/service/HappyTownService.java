package com.example.happytownclone.service;

import com.example.happytownclone.domain.Cadeau;
import com.example.happytownclone.domain.Habitant;
import com.example.happytownclone.domain.TrancheAge;
import com.example.happytownclone.repository.HabitantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Component
@Transactional
public class HappyTownService {

    private final HabitantRepository habitantRepository;
    private final Random random;

    public HappyTownService(HabitantRepository habitantRepository) {
        this.habitantRepository = habitantRepository;
        this.random = new Random();
    }

    public void attribuerCadeaux(String fileName, LocalDate dateCourante, String smtpHost, int smtpPort) throws MessagingException, IOException {

        Map<TrancheAge, List<Cadeau>> cadeauxByTrancheAge = buildCadeauxByTrancheAge(fileName);
        List<Habitant> habitantsEligibles = habitantRepository
                .findByDateArriveeCommuneLessThanEqualAndCadeauOffertIsNullAndDateAttributionCadeauIsNullOrderByDateArriveeCommune(dateCourante);
        List<Habitant> habitantsAttributionCadeaux = new ArrayList<>();

        for (Habitant habitant : habitantsEligibles) {
            Optional<TrancheAge> trancheAge = getTrancheAgeCadeaux(dateCourante, habitant, cadeauxByTrancheAge.keySet());
            if (trancheAge.isPresent()) {
                List<Cadeau> cadeauxPossibles = cadeauxByTrancheAge.get(trancheAge.get());
                Cadeau randomCadeau = cadeauxPossibles.get(random.nextInt(cadeauxPossibles.size()));
                envoiMessage(smtpHost, smtpPort, habitant, randomCadeau);
                habitant.setCadeauOffert(randomCadeau.getDetail());
                habitant.setDateAttributionCadeau(dateCourante);
                habitantRepository.save(habitant);
                habitantsAttributionCadeaux.add(habitant);
            }
        }
        envoiMessageSyntheseCadeauxJournee(smtpHost, smtpPort, habitantsAttributionCadeaux, dateCourante);
    }

    private Map<TrancheAge, List<Cadeau>> buildCadeauxByTrancheAge(String fileName) throws IOException {
        TrancheAgeComparator trancheAgeComparator = new TrancheAgeComparator();
        Map<TrancheAge, List<Cadeau>>  cadeauxByTrancheAge = new TreeMap<>(trancheAgeComparator);
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        in.readLine();
        String line = "";
        while ((line = in.readLine()) != null) {
            String[] cadeaux = line.split(",");
            String reference = cadeaux[0];
            String description = cadeaux[1];
            BigDecimal montant = new BigDecimal(cadeaux[2]);
            String[] trancheAgeData = cadeaux[3].split("-");
            TrancheAge trancheAge = new TrancheAge(Integer.parseInt(trancheAgeData[0]), Integer.parseInt(trancheAgeData[1]));
            if (!cadeauxByTrancheAge.containsKey(trancheAge)) {
                cadeauxByTrancheAge.put(trancheAge, new ArrayList<>());
            }
            Cadeau cadeau = Cadeau.builder()
                    .reference(reference)
                    .description(description)
                    .montant(montant)
                    .trancheAge(trancheAge)
                    .build();
            cadeauxByTrancheAge.get(trancheAge).add(cadeau);
        }

        return cadeauxByTrancheAge;
    }

    private Optional<TrancheAge> getTrancheAgeCadeaux(LocalDate dateCourante, Habitant habitant, Set<TrancheAge> trancheAges) {
        Optional<TrancheAge> optionalTrancheAge = Optional.empty();
        int ageHabitant = Period.between(habitant.getDateNaissance(), dateCourante).getYears();
        for (TrancheAge tranche : trancheAges) {
            if (ageHabitant >= tranche.getAgeMin() && ageHabitant < tranche.getAgeMax()) {
                optionalTrancheAge = Optional.of(tranche);
            }
        }

        return optionalTrancheAge;
    }

    private void envoiMessage(String smtpHost, int smtpPort, Habitant habitant, Cadeau randomCadeau) throws MessagingException {
        String subject = "Happy Birthday in HappyTown!";
        String beneficiaire = habitant.getEmail();
        String body = "Bonjour " + habitant.getPrenom() + " " + habitant.getNom() + ",";
        body += "\n\nFélicitation, pour fêter votre premier anniversaire dans notre merveilleuse ville HappyTown, la mairie a le plaisir de vous offrir un cadeau de bienvenue.";
        body += "\n\nVotre cadeau est " + randomCadeau.getDetail();
        body += "\n\nL'équipe HappyTown";

        envoiMail(smtpHost, smtpPort, subject, beneficiaire, body);
    }

    private void envoiMessageSyntheseCadeauxJournee(String smtpHost, int smtpPort, List<Habitant> habitantsAttributionCadeaux, LocalDate dateCourante) throws MessagingException {
        if (habitantsAttributionCadeaux.isEmpty()) {
            String subject = String.format("%1$td/%1$tm/%1$tY", dateCourante) + " - Synthèse des cadeaux pour envoi";
            String beneficiaire = "mairie+service-cadeau@happytown.com";
            String body = "Bonjour,";
            body += "\n\nVoici la liste récapitulative des cadeaux du jour : ";
            for (Habitant habitant : habitantsAttributionCadeaux) {
                body += " \n* " + habitant.getPrenom() + " " + habitant.getNom() + " : " + habitant.getCadeauOffert();
            }
            body += "\n\nMerci!";
            envoiMail(smtpHost, smtpPort, subject, beneficiaire, body);
        }
    }

    public void envoiMail(String smtpHost, int smtpPort, String subject, String beneficiaire, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getInstance(props, null);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("mairie@happytown.com"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(beneficiaire));
        msg.setSubject(subject);
        msg.setText(body);

        Transport.send(msg);
    }
}
