package com.algaworks.algasensors.temperature.processing.api.controller;

import com.algaworks.algasensors.temperature.processing.api.model.TemperatureLogOutput;
import com.algaworks.algasensors.temperature.processing.common.IdGenerator;
import io.hypersistence.tsid.TSID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

@Slf4j
@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures/data")
public class TemperatureProcessingController {

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public void data(@PathVariable TSID sensorId, @RequestBody String input) {
        if (StringUtils.isEmpty(input)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        double temperature;
        try {
            temperature = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        TemperatureLogOutput temperatureLogOutput = new TemperatureLogOutput(
                IdGenerator.generateTimeBaseUUID(),
                sensorId,
                OffsetDateTime.now(),
                temperature
        );

        log.info(temperatureLogOutput.toString());
    }
}
