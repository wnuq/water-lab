package com.whater.lab.cup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class PhysicochemicalExamination {

    @Id
    @GeneratedValue
    private Long id;

    private double ph;

    private SolutionReaction solutionReaction;

    private String description;

    private ExaminationStatus status;

    @OneToOne
    private Sample sample;
}
