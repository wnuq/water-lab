package com.whater.lab.cup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PhysicochemicalExamination {

    @Id
    @GeneratedValue
    private Long id;

    private Long ph;

    private ExaminationStatus status;
}
