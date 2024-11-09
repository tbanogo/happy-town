package com.example.happytownclone.core.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Cadeau {

    private String reference;
    private String description;
    private BigDecimal montant;
    private TrancheAge trancheAge;

    public Cadeau(String reference, String description, BigDecimal montant, TrancheAge trancheAge) {
        this.reference = reference;
        this.description = description;
        this.montant = montant;
        this.trancheAge = trancheAge;
    }

    public String getDetail() {
        return this.description + " " +
                "(Montant : " + this.montant + "$ - " +
                "Référence : " + this.trancheAge + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cadeau that = (Cadeau) obj;
        return Objects.equals(reference, that.reference) &&
                Objects.equals(description, that.description) &&
                Objects.equals(montant, that.montant)
                && Objects.equals(trancheAge, that.trancheAge);
    }
}
