package com.whater.lab.cup.examination;

import com.whater.lab.cup.dto.PhysicochemicalExaminationDto;
import com.whater.lab.cup.service.SampleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class PhysicochemicalExaminationService {

    private PhysicochemicalExaminationRepository examinationRepository;

    private SampleService sampleService;


    public List<PhysicochemicalExaminationDto> findAll() {
        return examinationRepository.findAll().stream()
                .map(e -> e.toDto())
                .toList();
    }

    public PhysicochemicalExaminationDto find(Long id) {
        return findExamination(id).toDto();
    }

    public Long addPhysicochemicalExamination(Long sampleId) {
        return examinationRepository.save(new PhysicochemicalExamination()).getId();
    }

    @Transactional
    public void updatePhysicochemicalExamination(PhysicochemicalExaminationDto dto) {
        PhysicochemicalExamination examination = findExamination(dto.id());

        examination.setPh(dto.ph());
        examination.setDescription(dto.description());

        examinationRepository.save(examination);
    }

    @Transactional
    public void finishPhysicochemicalExamination(Long id, String description) {
        PhysicochemicalExamination examination = findExamination(id);
        var whenTake = sampleService.getWhenTakeDate(examination.getSampleId());

        examination.finishExamination(whenTake, description);

        examinationRepository.save(examination);
    }

    @Transactional
    public void failPhysicochemicalExamination(Long id, String description) {
        PhysicochemicalExamination examination = findExamination(id);
        examination.failExamination(description);

        examinationRepository.save(examination);
    }

    private PhysicochemicalExamination findExamination(Long id) {
        return examinationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No examination found"));
    }
}
