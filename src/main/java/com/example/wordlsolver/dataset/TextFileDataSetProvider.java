package com.example.wordlsolver.dataset;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

/**
 *
 *
 * @author DaleH
 *
 */
@Log4j2
@Repository
public class TextFileDataSetProvider implements DataSetProvider{



    @Override
    public List<String> getDataSet() {
        List<String> dataset = Collections.emptyList();
        try{
            dataset = Files.readAllLines(Paths.get("src/main/resources/dataset.txt"));
        } catch (Exception e) {
            log.error("Error loading in dataset", e);
        }

        return dataset;
    }
}
