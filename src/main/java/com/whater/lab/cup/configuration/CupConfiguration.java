package com.whater.lab.cup.configuration;

import com.whater.lab.cup.repository.MicrobiologicalExaminationRepository;
import com.whater.lab.cup.repository.PhysicochemicalExaminationRepository;
import com.whater.lab.cup.repository.SampleRepository;
import com.whater.lab.cup.service.MicrobiologicalExaminationService;
import com.whater.lab.cup.service.PhysicochemicalExaminationService;
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
    public PhysicochemicalExaminationService physicochemicalExaminationService(
            PhysicochemicalExaminationRepository physicochemicalExaminationRepository) {
        return new PhysicochemicalExaminationService(physicochemicalExaminationRepository);
    }

    @Bean
    public MicrobiologicalExaminationService microbiologicalExaminationService(
            MicrobiologicalExaminationRepository microbiologicalExaminationRepository
    ) {
        return new MicrobiologicalExaminationService(microbiologicalExaminationRepository);
    }
}
