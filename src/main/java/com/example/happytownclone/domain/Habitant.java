package com.example.happytownclone.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "habitant")
@JsonPropertyOrder({"id", "nom", "prenom", "email", "dateNaissance", "dateArriveeCommune", "adressePostale", "cadeauOffert", "dateAttributionCadeau"})
public class Habitant {

    @Id
    @JsonProperty(index = 0)
    private String id;
    @JsonProperty(index = 1)
    private String nom;
    @JsonProperty(index = 2)
    private String prenom;
    @JsonProperty(index = 3)
    private String email;
    @JsonProperty(index = 4)
    private LocalDate dateNaissance;
    @JsonProperty(index = 5)
    private LocalDate dateArriveeCommune;
    @JsonProperty(index = 6)
    private String adressePostale;
    @JsonProperty(index = 7)
    private String cadeauOffert;
    @JsonProperty(index = 8)
    private LocalDate dateAttributionCadeau;

}
