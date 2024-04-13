package com.whater.lab.cup.configuration;

import com.whater.lab.cup.examination.MicrobiologicalExaminationRepository;
import com.whater.lab.cup.examination.PhysicochemicalExaminationRepository;
import com.whater.lab.cup.examination.MicrobiologicalExaminationService;
import com.whater.lab.cup.examination.PhysicochemicalExaminationService;
import com.whater.lab.cup.service.SampleService;
import com.whater.lab.cup.service.SetOfSamplesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CupConfiguration {

    @Bean
    public SetOfSamplesService setOfSamplesService() {
        return new SetOfSamplesService();
    }

    @Bean
    public SampleService sampleService() { return new SampleService(); }

    @Bean
    public PhysicochemicalExaminationService physicochemicalExaminationService(
            PhysicochemicalExaminationRepository physicochemicalExaminationRepository,
            SampleService sampleService) {
        return new PhysicochemicalExaminationService(physicochemicalExaminationRepository, sampleService);
    }

    @Bean
    public MicrobiologicalExaminationService microbiologicalExaminationService(
            MicrobiologicalExaminationRepository microbiologicalExaminationRepository
    ) {
        return new MicrobiologicalExaminationService(microbiologicalExaminationRepository);
    }
}
