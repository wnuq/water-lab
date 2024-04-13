package com.whater.lab.cup.examination;

import jakarta.persistence.Embeddable;

@Embeddable
public class Ph {

    private double phValue;

    public Ph() {
        this.phValue = -1;
    }

    public Ph(double phValue) {
        if(phValue < 0 || phValue > 14) {
            throw new IllegalArgumentException("PH should be in range 0-14");
        }

        this.phValue = phValue;
    }

    public double getPhValue() {
        return this.phValue;
    }
}
