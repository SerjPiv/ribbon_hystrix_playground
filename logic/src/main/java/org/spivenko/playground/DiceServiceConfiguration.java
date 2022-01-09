package org.spivenko.playground;

import org.spivenko.playground.integration.RandomNumberServiceFeignClient;
import org.spivenko.playground.service.DiceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiceServiceConfiguration {

    @Bean
    public DiceService service(RandomNumberServiceFeignClient randomNumberServiceClient) {
        return new DiceService(randomNumberServiceClient);
    }
}
