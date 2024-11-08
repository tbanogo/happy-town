package com.example.happytownclone.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cadeau {

    @NotBlank
    private String reference;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal montant;
    @NotNull
    private TrancheAge trancheAge;

    public String getDetail() {
        return this.description + " " +
                "(Montant : " + this.montant + "$ - " +
                "Référence : " + this.reference + ")";
    }
}
