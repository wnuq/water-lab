package com.whater.lab.cup.entity;

import com.whater.lab.cup.examination.ExaminationStatus;
import com.whater.lab.cup.examination.MicrobiologicalExamination;
import com.whater.lab.cup.examination.PhysicochemicalExamination;
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

    private SampleState state;

    @ManyToOne
    private SetOfSamples setOfSamples;

    @OneToOne
    private MicrobiologicalExamination microbiologicalExamination;

    @OneToOne
    private PhysicochemicalExamination physicochemicalExamination;

    public void tryFinalizeSample() {
        if (physicochemicalExamination.getStatus() == ExaminationStatus.FINISHED &&
                microbiologicalExamination.getStatus() == ExaminationStatus.FINISHED) {
            this.state = SampleState.FINISHED;
        }
    }
}
