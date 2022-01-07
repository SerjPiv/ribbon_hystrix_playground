package org.spivenko.playground.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "random-number-service-client",
        path = "/")
public interface RandomNumberServiceClient {

    @GetMapping(value = "/v1/random", produces = MediaType.APPLICATION_JSON_VALUE)
    int getRandomNumber(@RequestParam("min") int min, @RequestParam("max") int max, @RequestParam("count") int count);
}
