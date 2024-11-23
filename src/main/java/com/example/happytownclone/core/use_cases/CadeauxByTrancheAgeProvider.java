package com.example.happytownclone.core.use_cases;

import com.example.happytownclone.core.entities.Cadeau;
import com.example.happytownclone.core.entities.TrancheAge;

import java.util.List;
import java.util.Map;

public interface CadeauxByTrancheAgeProvider {

    Map<TrancheAge, List<Cadeau>> get() throws CadeauxByTrancheAgeException;

}
