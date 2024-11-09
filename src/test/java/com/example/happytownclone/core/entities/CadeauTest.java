package com.example.happytownclone.core.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;

import static com.example.happytownclone.fixtures.TrancheAgeFixture.trancheAge_6_10;

public class CadeauTest {


    @Test
    public void getDetail() {
        // Given
        BigDecimal montant = BigDecimal.valueOf(7.99);
        String descrption = "Puissance 4 voyage";
        String reference = "dbe982da";
        Cadeau cadeau = new Cadeau(reference, descrption, montant, trancheAge_6_10());

        // When
        String detail = cadeau.getDetail();

        // Then
        assertThat(detail).isEqualTo("Puissance 4 voyage (Montant : 7.99$ - Référence : dbe982da");
    }
}
