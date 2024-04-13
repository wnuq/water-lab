package com.whater.lab.cup.examination;

import com.whater.lab.cup.dto.MicrobiologicalExaminationDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

import static com.whater.lab.cup.examination.ExaminationStatus.*;
import static java.time.LocalDateTime.now;

@AllArgsConstructor
public class MicrobiologicalExaminationService {

    private MicrobiologicalExaminationRepository examinationRepository;

    public List<MicrobiologicalExaminationDto> findAll() {
        List<MicrobiologicalExamination> examinationList = examinationRepository.findAll();

        return examinationList.stream().map(e ->
                        new MicrobiologicalExaminationDto(
                                e.getId(),
                                e.getBaseOfTheExponentiation(),
                                e.getExponentOfBacteriaNumber(),
                                e.getDescription(),
                                e.getStatus()))
                .toList();
    }

    public MicrobiologicalExaminationDto find(Long id) {
        MicrobiologicalExamination examination = findExamination(id);

        return new MicrobiologicalExaminationDto(
                examination.getId(),
                examination.getBaseOfTheExponentiation(),
                examination.getExponentOfBacteriaNumber(),
                examination.getDescription(),
                examination.getStatus());
    }

    public Long addMicrobiologicalExamination(Long sampleId) {
        MicrobiologicalExamination examination = new MicrobiologicalExamination();
        examination.setBaseOfTheExponentiation(10L);
        examination.setStatus(NOT_STARTED);

        return examinationRepository.save(examination).getId();
    }

    @Transactional
    public void updateMicrobiologicalExamination(MicrobiologicalExaminationDto dto) {
        MicrobiologicalExamination examination = findExamination(dto.id());

        if(dto.baseOfTheExponentiation() < 0 || dto.exponentOfBacteriaNumber() < 0) {
            throw new IllegalArgumentException("Base of the exponentiation or exponent can't be negative");
        }

        examination.setBaseOfTheExponentiation(dto.baseOfTheExponentiation());
        examination.setExponentOfBacteriaNumber(dto.exponentOfBacteriaNumber());
        examination.setDescription(dto.description());

        examinationRepository.save(examination);
    }

    @Transactional
    public void finishMicrobiologicalExamination(Long id, String description) {
        MicrobiologicalExamination examination = findExamination(id);

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
        MicrobiologicalExamination examination = findExamination(id);

        examination.setStatus(FAIL);
        examination.setDescription(description);

        examinationRepository.save(examination);
    }

    private MicrobiologicalExamination findExamination(Long id) {
        return examinationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No examination found"));
    }
}
