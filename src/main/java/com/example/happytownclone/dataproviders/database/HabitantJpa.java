package com.example.happytownclone.dataproviders.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "habitant")
public class HabitantJpa {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateNaissance;
    private LocalDate dateArriveeCommune;
    private String adressePostale;
    private String cadeauOffert;
    private LocalDate dateAttributionCadeau;

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public LocalDate getDateArriveeCommune() {
        return dateArriveeCommune;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public String getCadeauOffert() {
        return cadeauOffert;
    }

    public LocalDate getDateAttributionCadeau() {
        return dateAttributionCadeau;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setDateArriveeCommune(LocalDate dateArriveeCommune) {
        this.dateArriveeCommune = dateArriveeCommune;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public void setCadeauOffert(String cadeauOffert) {
        this.cadeauOffert = cadeauOffert;
    }

    public void setDateAttributionCadeau(LocalDate dateAttributionCadeau) {
        this.dateAttributionCadeau = dateAttributionCadeau;
    }
}
