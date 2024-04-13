package com.whater.lab.cup.examination;

import com.whater.lab.cup.dto.PhysicochemicalExaminationDto;
import com.whater.lab.cup.entity.Sample;
import com.whater.lab.cup.entity.SolutionReaction;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import static com.whater.lab.cup.entity.SolutionReaction.*;
import static com.whater.lab.cup.examination.ExaminationStatus.*;
import static java.time.LocalDateTime.now;

@Data
@Entity
public class PhysicochemicalExamination {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Ph ph;

    private SolutionReaction solutionReaction;

    private String description;

    private ExaminationStatus status;

    @OneToOne
    private Sample sample;

    public PhysicochemicalExamination() {
        this.ph = new Ph();
        this.status = NOT_STARTED;
    }

    public void setPh(double phValue) {
        this.ph = new Ph(phValue);
        setSolutionReaction(phValue);
    }

    private void setSolutionReaction(double phValue) {
        if(phValue == 7) {
            this.solutionReaction = NEUTRAL;
        } else if (phValue < 7) {
            this.solutionReaction = ACIDIC;
        } else if (phValue > 7) {
            this.solutionReaction = ALKALINE;
        }
    }

    public void finishExamination(LocalDateTime whenTake, String description) {
        if(whenTake.plusDays(3).isAfter(now())) {
            this.status = FAIL;
            this.description += " Can't examine sample that have more than 3 days.";
        } else {
            this.status = FINISHED;
            this.description = description;
        }
    }

    public void failExamination(String description) {
        this.status = FAIL;
        this.description = description;
    }

    public Long getSampleId() {
        return this.sample.getId();
    }

    public PhysicochemicalExaminationDto toDto() {
        return new PhysicochemicalExaminationDto(
                getId(),
                getPh().getPhValue(),
                getSolutionReaction(),
                getDescription(),
                getStatus());
    }
}
