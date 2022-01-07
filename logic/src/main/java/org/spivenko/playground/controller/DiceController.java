package org.spivenko.playground.controller;

import lombok.AllArgsConstructor;
import org.spivenko.playground.service.DiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
public class DiceController {

    private final DiceService service;

    @GetMapping(path = "/playground/dice/throw")
    public ResponseEntity<Integer> throwTheDice() {
        return ok(service.throwTheDice());
    }
}
