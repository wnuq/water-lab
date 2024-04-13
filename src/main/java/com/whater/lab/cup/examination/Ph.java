package com.whater.lab.cup.examination;

import com.whater.lab.cup.entity.SolutionReaction;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import static com.whater.lab.cup.entity.SolutionReaction.*;

@Embeddable
public class Ph {

    @Getter
    private double phValue;

    @Getter
    private SolutionReaction solutionReaction;

    public Ph() {
        this.phValue = -1;
    }

    public Ph(double phValue) {
        if(phValue < 0 || phValue > 14) {
            throw new IllegalArgumentException("PH should be in range 0-14");
        }

        this.phValue = phValue;
        setSolutionReaction();
    }

    private void setSolutionReaction() {
        if(phValue == 7) {
            this.solutionReaction = NEUTRAL;
        } else if (phValue < 7) {
            this.solutionReaction = ACIDIC;
        } else if (phValue > 7) {
            this.solutionReaction = ALKALINE;
        }
    }
}
