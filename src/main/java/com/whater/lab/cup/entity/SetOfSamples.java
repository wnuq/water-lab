package com.whater.lab.cup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SetOfSamples {

    @Id
    @GeneratedValue
    private Long id;

    private String number;

    private String description;

    private SetStatus status;

    @OneToMany(mappedBy = "setOfSamples")
    private List<Sample> sampleList = new ArrayList<>();
}
