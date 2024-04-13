package com.whater.lab.cup.dto;

import com.whater.lab.cup.entity.ExaminationStatus;

public record MicrobiologicalExaminationDto(
        Long id,
        Long baseOfTheExponentiation,
        Long exponentOfBacteriaNumber,
        String description,
        ExaminationStatus status) {
}
