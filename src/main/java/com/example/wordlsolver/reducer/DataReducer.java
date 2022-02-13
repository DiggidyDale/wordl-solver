package com.example.wordlsolver.reducer;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 *
 *
 * @author DaleH
 *
 */
@Service
public class DataReducer implements Reducer {


    @Override
    public List<String> getReducedList(List<String> currentList, List<String> goodLetters, List<String> badLetters) {

        final Predicate<String> hasLettersWeWant = word -> {
          for(String letter : goodLetters){
               if (!word.contains(letter)){
                  return false;
               }
          }
          return true;
        };

        final Predicate<String> removeWordsWeDontWant = word -> {
            for(String letter : badLetters){
                if(word.contains(letter)){
                    return false;
                }
            }
            return true;
        };

        return currentList.stream()
                .filter(hasLettersWeWant)
                .filter(removeWordsWeDontWant)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getReducedList(List<String> currentList, Map<Integer, String> confirmedLetters){
        final Predicate<String> removeWords = word -> {
            Set<Integer> keys = confirmedLetters.keySet();

            for(Integer key : keys){
                if(!word.split("")[key].equals(confirmedLetters.get(key))){
                    return false;
                }
            }
            return true;
        };

        return  currentList.stream()
                .filter(removeWords)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getReducedListByIndex(List<String> currentList, Map<Integer, List<String>> confirmedNotIndex) {
        final Predicate<String> removeWord = word -> {
            Set<Integer> keys = confirmedNotIndex.keySet();

            for(Integer key :keys){
                if(confirmedNotIndex.get(key).contains(word.split("")[key])){
                    return false;
                }
            }
            return true;
        };

        return currentList.stream()
                .filter(removeWord)
                .toList();
    }
}
