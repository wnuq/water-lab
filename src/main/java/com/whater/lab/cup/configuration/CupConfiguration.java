package com.whater.lab.cup.configuration;

import com.whater.lab.cup.repository.MicrobiologicalExaminationRepository;
import com.whater.lab.cup.repository.PhysicochemicalExaminationRepository;
import com.whater.lab.cup.repository.SampleRepository;
import com.whater.lab.cup.repository.SetOfSamplesRepository;
import com.whater.lab.cup.service.MicrobiologicalExaminationService;
import com.whater.lab.cup.service.PhysicochemicalExaminationService;
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
    SampleService sampleService(SampleRepository sampleRepository,
                                SetOfSamplesRepository setOfSamplesRepository) {
        return new SampleService(sampleRepository, setOfSamplesRepository);
    }

    @Bean
    public PhysicochemicalExaminationService physicochemicalExaminationService(
            PhysicochemicalExaminationRepository physicochemicalExaminationRepository,
            SampleService sampleService) {
        return new PhysicochemicalExaminationService(physicochemicalExaminationRepository, sampleService);
    }

    @Bean
    public MicrobiologicalExaminationService microbiologicalExaminationService(
            MicrobiologicalExaminationRepository microbiologicalExaminationRepository,
            SampleService sampleService
    ) {
        return new MicrobiologicalExaminationService(microbiologicalExaminationRepository, sampleService);
    }
}
