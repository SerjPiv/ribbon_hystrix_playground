package org.spivenko.playground;

import org.spivenko.playground.integration.RandomNumberServiceClient;
import org.spivenko.playground.service.DiceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiceServiceConfiguration {

    @Bean
    public DiceService service(RandomNumberServiceClient randomNumberServiceClient) {
        return new DiceService(randomNumberServiceClient);
    }
}
