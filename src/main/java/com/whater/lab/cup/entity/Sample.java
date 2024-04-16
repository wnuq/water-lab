package com.whater.lab.cup.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Sample {

    @Id
    @GeneratedValue
    private Long id;

    private String number;

    private String city;

    private String street;

    private String buildingNumber;

    private String clientPhoneNumber;

    private LocalDateTime whenTake;

    private SampleStatus status;

    @ManyToOne
    private SetOfSamples setOfSamples;

    @OneToOne
    private MicrobiologicalExamination microbiologicalExamination;

    @OneToOne
    private PhysicochemicalExamination physicochemicalExamination;
}
