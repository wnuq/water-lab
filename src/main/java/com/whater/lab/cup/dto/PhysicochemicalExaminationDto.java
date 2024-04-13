package com.whater.lab.cup.dto;

import com.whater.lab.cup.entity.ExaminationStatus;
import com.whater.lab.cup.entity.SolutionReaction;

public record PhysicochemicalExaminationDto(
        Long id,
        double ph,
        SolutionReaction solutionReaction,
        String description,
        ExaminationStatus status) {
}
