package com.whater.lab.cup.service;

import com.whater.lab.cup.dto.PhysicochemicalExaminationDto;
import com.whater.lab.cup.entity.PhysicochemicalExamination;
import com.whater.lab.cup.repository.PhysicochemicalExaminationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

import static com.whater.lab.cup.entity.ExaminationStatus.*;
import static com.whater.lab.cup.entity.SolutionReaction.*;
import static java.time.LocalDateTime.now;

@AllArgsConstructor
public class PhysicochemicalExaminationService {

    private PhysicochemicalExaminationRepository examinationRepository;


    public List<PhysicochemicalExaminationDto> findAll() {
        List<PhysicochemicalExamination> examinationList = examinationRepository.findAll();

        return examinationList.stream().map(e ->
                new PhysicochemicalExaminationDto(
                        e.getId(),
                        e.getPh(),
                        e.getSolutionReaction(),
                        e.getDescription(),
                        e.getStatus()))
                .toList();
    }

    public PhysicochemicalExaminationDto find(Long id) {
        PhysicochemicalExamination examination = findExamination(id);

        return new PhysicochemicalExaminationDto(
                examination.getId(),
                examination.getPh(),
                examination.getSolutionReaction(),
                examination.getDescription(),
                examination.getStatus());
    }

    public Long addPhysicochemicalExamination(Long sampleId) {
        PhysicochemicalExamination examination = new PhysicochemicalExamination();

        examination.setPh(-1);
        examination.setStatus(NOT_STARTED);

        return examinationRepository.save(examination).getId();
    }

    @Transactional
    public void updatePhysicochemicalExamination(PhysicochemicalExaminationDto dto) {
        PhysicochemicalExamination examination = findExamination(dto.id());

        if(dto.ph() < 0 || dto.ph() > 14) {
            throw new IllegalArgumentException("PH should be in range 0-14");
        }

        examination.setPh(dto.ph());

        if(examination.getPh() == 7) {
            examination.setSolutionReaction(NEUTRAL);
        } else if (examination.getPh() < 7) {
            examination.setSolutionReaction(ACIDIC);
        } else if (examination.getPh() > 7) {
            examination.setSolutionReaction(ALKALINE);
        }

        examination.setPh(dto.ph());
        examination.setDescription(dto.description());

        examinationRepository.save(examination);
    }

    @Transactional
    public void finishPhysicochemicalExamination(Long id, String description) {
        PhysicochemicalExamination examination = findExamination(id);

        if(examination.getSample().getWhenTake().plusDays(3).isAfter(now())) {
            examination.setStatus(FAIL);
            examination.setDescription(examination.getDescription() + " Can't examine sample that have more than 3 days.");
        }

        examination.setStatus(FINISHED);
        examination.setDescription(description);

        examinationRepository.save(examination);
    }

    @Transactional
    public void failPhysicochemicalExamination(Long id, String description) {
        PhysicochemicalExamination examination = findExamination(id);

        examination.setStatus(FAIL);
        examination.setDescription(description);

        examinationRepository.save(examination);
    }

    private PhysicochemicalExamination findExamination(Long id) {
        return examinationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No examination found"));
    }
}
