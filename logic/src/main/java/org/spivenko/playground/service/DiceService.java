package org.spivenko.playground.service;

import lombok.RequiredArgsConstructor;
import org.spivenko.playground.integration.RandomNumberServiceFeignClient;
import org.spivenko.playground.integration.model.Result;

@RequiredArgsConstructor
public class DiceService {

    private final RandomNumberServiceFeignClient client;

    public Result throwTheDice() {
        return client.getRandomNumber(1, 6, 1);
    }
}
