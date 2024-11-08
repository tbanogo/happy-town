package com.example.happytownclone.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrancheAge {

    @NotNull
    private int ageMin;
    @NotNull
    private int ageMax;

}
