package com.whater.lab.cup.configuration;

import com.whater.lab.cup.service.SetOfSamplesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CupConfiguration {

    @Bean
    public SetOfSamplesService setOfSamplesService() {
        return new SetOfSamplesService();
    }
}
