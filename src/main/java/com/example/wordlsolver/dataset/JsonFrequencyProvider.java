package com.example.wordlsolver.dataset;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

/**
 *
 *
 * @author DaleH
 *
 */
@Log4j2
@Repository
public class JsonFrequencyProvider implements FrequenyProvider {

    @Override public Map<String, Double> getFrequencyMap() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Double> frequencyMap = new HashMap<>();
        try {
            frequencyMap = mapper.readValue(Paths.get("src/main/resources/frequency.json").toFile(), Map.class);
        } catch (IOException e) {
            log.error("Error getting frequency", e);
        }
        return frequencyMap;
    }
}
