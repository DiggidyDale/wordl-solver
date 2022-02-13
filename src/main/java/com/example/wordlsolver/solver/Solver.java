package com.example.wordlsolver.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.wordlsolver.dataset.DataSetProvider;
import com.example.wordlsolver.dataset.FrequenyProvider;
import com.example.wordlsolver.reducer.Reducer;

/**
 *
 *
 * @author DaleH
 *
 */
@Component
public class Solver {

    private final Reducer reducer;

    private final DataSetProvider dataSetProvider;

    private final FrequenyProvider frequenyProvider;

    private List<String> currentList;

    private List<String> badLetters;

    private List<String> goodLetters;

    private Map<Integer, String> confirmedLetters;

    private Map<Integer, List<String>> confirmedNotIndex;

    private Map<String, Double> frequencyMap;

    @Autowired
    public Solver(Reducer reducer, DataSetProvider dataSetProvider, FrequenyProvider frequenyProvider) {
        this.reducer = reducer;
        this.dataSetProvider = dataSetProvider;
        this.frequenyProvider = frequenyProvider;

        initialiseData();
    }


    public List<String> addGoodLetters(String newGoodLetters){
        goodLetters.addAll(List.of(newGoodLetters.split("")));

        return goodLetters;
    }

    public List<String> addBadLetters(String newBadLetters){
        badLetters.addAll(List.of(newBadLetters.split("")));

        return badLetters;
    }

    public Map<Integer, String> addConfirmedLetters(int index, String letter){
        confirmedLetters.put(index, letter);

        return confirmedLetters;
    }

    public Map<Integer, List<String>> addConfirmedBadLetters(int index, String letter){
        List<String> letters = new ArrayList<>();
        letters.add(letter);
        if(this.confirmedNotIndex.containsKey(index)){
            letters.addAll(this.confirmedNotIndex.get(index));
        }
        this.confirmedNotIndex.put(index, letters);

        return confirmedNotIndex;
    }

    public Map<String, Double> getTopGuesses(){
        this.currentList = reducer.getReducedList(this.currentList, this.goodLetters, this.badLetters);
        this.currentList = reducer.getReducedList(this.currentList, this.confirmedLetters);
        this.currentList = reducer.getReducedListByIndex(this.currentList, this.confirmedNotIndex);

        return this.sortByFrequency();
    }

    public void resetGame(){
        this.initialiseData();
    }

    private Map<String, Double> sortByFrequency(){
        return this.currentList.stream()
                .collect(Collectors.toMap(Function.identity(), word -> this.frequencyMap.get(word)))
                .entrySet().stream()
                .sorted(Collections.reverseOrder(Entry.comparingByValue()))
                .limit(10)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    private void initialiseData() {
        this.currentList = this.dataSetProvider.getDataSet();
        this.badLetters = new ArrayList<>();
        this.goodLetters = new ArrayList<>();
        this.confirmedLetters = new HashMap<>();
        this.confirmedNotIndex = new HashMap<>();
        this.frequencyMap = this.frequenyProvider.getFrequencyMap();
    }

}
