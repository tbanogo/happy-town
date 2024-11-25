package com.example.happytownclone.entities;

import com.example.happytownclone.entities.trancheAge.TrancheAge;

import java.util.Comparator;

public class TrancheAgeComparator implements Comparator<TrancheAge> {

    @Override
    public int compare(TrancheAge o1, TrancheAge o2) {
        Integer sumAnneesTranche1 = o1.getAgeMin() + o1.getAgeMax();
        Integer sumAnneesTranche2 = o2.getAgeMin() + o2.getAgeMax();
        return sumAnneesTranche1.compareTo(sumAnneesTranche2);
    }

}
