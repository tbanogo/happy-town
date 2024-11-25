package com.example.happytownclone.entities.habitant.model;

import com.example.happytownclone.commons.model.AbstractBaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Habitant extends AbstractBaseModel<String> {

    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateNaissance;
    private LocalDate dateArriveeCommune;
    private String adressePostale;
    private String cadeauOffert;
    private LocalDate dateAttributionCadeau;

    public boolean hasCadeau() {
        return cadeauOffert != null &&
                dateAttributionCadeau != null;
    }

    public void attribuerCadeau(String cadeauOffert, LocalDate dateAttributionCadeau) {
        this.cadeauOffert = cadeauOffert;
        this.dateAttributionCadeau = dateAttributionCadeau;
    }

}
