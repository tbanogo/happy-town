package com.example.happytownclone.core.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.Set;
import java.util.TreeSet;

import static com.example.happytownclone.fixtures.TrancheAgeFixture.*;

public class TrancheAgeComparatorTest {

    TrancheAgeComparator trancheAgeComparator = new TrancheAgeComparator();

    @Test
    void comparatorByTrancheAge_shouldCompareByAgeMinAndAgeMax() {
        // Given
        Set<TrancheAge> trancheAges = new TreeSet<>(trancheAgeComparator);

        // When
        trancheAges.add(trancheAge_10_15());
        trancheAges.add(trancheAge_6_10());
        trancheAges.add(trancheAge_30_40());

        // Then
        assertThat(trancheAges).containsExactly(trancheAge_6_10(), trancheAge_10_15(), trancheAge_30_40());
    }
}
