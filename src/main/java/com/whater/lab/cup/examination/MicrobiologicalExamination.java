package com.whater.lab.cup.examination;

import com.whater.lab.cup.entity.Sample;
import com.whater.lab.cup.examination.ExaminationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class MicrobiologicalExamination {

    @Id
    @GeneratedValue
    private Long id;

    private Long baseOfTheExponentiation;

    private Long exponentOfBacteriaNumber;

    private String description;

    private ExaminationStatus status;

    @OneToOne
    private Sample sample;
}
