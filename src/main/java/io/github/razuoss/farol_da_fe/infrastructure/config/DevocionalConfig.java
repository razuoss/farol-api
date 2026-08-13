package io.github.razuoss.farol_da_fe.infrastructure.config;

import io.github.razuoss.farol_da_fe.domain.devocional.port.in.DevocionalUseCase;
import io.github.razuoss.farol_da_fe.domain.devocional.service.DevocionalService;
import io.github.razuoss.farol_da_fe.domain.shared.port.out.GenAiPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevocionalConfig {

    @Bean
    public DevocionalUseCase devocionalUseCase(GenAiPort genAiPort) {
        return new DevocionalService(genAiPort);
    }
}
