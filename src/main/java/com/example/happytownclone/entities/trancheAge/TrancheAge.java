package com.example.happytownclone.entities.trancheAge;

import java.util.Objects;

public class TrancheAge {

    private Integer ageMin;
    private Integer ageMax;

    public TrancheAge(int ageMin, int ageMax) {
        this.ageMin = ageMin;
        this.ageMax = ageMax;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TrancheAge that = (TrancheAge) obj;
        return Objects.equals(ageMin, that.getAgeMin()) &&
                Objects.equals(ageMax, that.getAgeMax());
    }
}
