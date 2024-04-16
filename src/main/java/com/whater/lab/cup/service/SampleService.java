package com.whater.lab.cup.service;

import com.whater.lab.cup.dto.SampleDto;
import com.whater.lab.cup.entity.*;
import com.whater.lab.cup.repository.SampleRepository;
import com.whater.lab.cup.repository.SetOfSamplesRepository;
import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;

import static com.whater.lab.cup.entity.ExaminationStatus.FAIL;
import static com.whater.lab.cup.entity.ExaminationStatus.FINISHED;

@AllArgsConstructor
public class SampleService {

    private SampleRepository sampleRepository;

    private SetOfSamplesRepository setOfSamplesRepository;

    public void add(SampleDto dto) {
        Sample sample = new Sample();

        sample.setNumber(dto.number());
        sample.setCity(dto.city());
        sample.setStreet(dto.street());
        sample.setBuildingNumber(dto.buildingNumber());
        sample.setClientPhoneNumber(dto.clientPhoneNumber());
        sample.setWhenTake(dto.whenTake());

        SetOfSamples setOfSamples = setOfSamplesRepository.findById(dto.setOfSampleId())
                .orElseThrow(() -> new NoSuchElementException("Set of sample don't exist"));

        sample.setSetOfSamples(setOfSamples);
        setOfSamples.getSampleList().add(sample);

        PhysicochemicalExamination physicochemicalExamination = new PhysicochemicalExamination();
        physicochemicalExamination.setStatus(ExaminationStatus.NOT_STARTED);

        sample.setPhysicochemicalExamination(physicochemicalExamination);
        physicochemicalExamination.setSample(sample);

        MicrobiologicalExamination microbiologicalExamination = new MicrobiologicalExamination();
        microbiologicalExamination.setStatus(ExaminationStatus.NOT_STARTED);

        sample.setMicrobiologicalExamination(microbiologicalExamination);
        microbiologicalExamination.setSample(sample);

        sampleRepository.save(sample);
    }

    public void updateSample(SampleDto dto) {
        Sample sample = sampleRepository.findById(dto.id())
                .orElseThrow(() -> new NoSuchElementException("Sample don't exist"));

        sample.setNumber(dto.number());
        sample.setCity(dto.city());
        sample.setStreet(dto.street());
        sample.setBuildingNumber(dto.buildingNumber());
        sample.setClientPhoneNumber(dto.clientPhoneNumber());
        sample.setWhenTake(dto.whenTake());

        sampleRepository.save(sample);
    }

    public void tryFinishSample(Long sampleId) {
        Sample sample = sampleRepository.findById(sampleId)
                .orElseThrow(() -> new NoSuchElementException("Sample don't exist"));

        if((sample.getPhysicochemicalExamination().getStatus() == FINISHED
                || sample.getPhysicochemicalExamination().getStatus() == FAIL)
                && (sample.getMicrobiologicalExamination().getStatus() == FINISHED
                || sample.getMicrobiologicalExamination().getStatus() == FAIL)) {
            sample.setStatus(SampleStatus.FINISHED);
        }

        sampleRepository.save(sample);
    }
}
