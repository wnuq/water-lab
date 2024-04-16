package com.whater.lab.cup.dto;

import java.time.LocalDateTime;

public record SampleDto(
        Long id,
        Long setOfSampleId,
        String number,
        String city,
        String street,
        String buildingNumber,
        String clientPhoneNumber,
        LocalDateTime whenTake
) {
}
