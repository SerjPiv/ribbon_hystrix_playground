package org.spivenko.playground.service;

import lombok.RequiredArgsConstructor;
import org.spivenko.playground.integration.RandomNumberServiceClient;

@RequiredArgsConstructor
public class DiceService {

    private final RandomNumberServiceClient client;

    public int throwTheDice() {
        return client.getRandomNumber(1, 6, 1);
    }
}
