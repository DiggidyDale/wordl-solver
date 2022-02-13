package com.example.wordlsolver.reducer;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author DaleH
 *
 */
public interface Reducer {

    List<String> getReducedList(List<String> currentList, List<String> goodLetters, List<String> badLetters);

    List<String> getReducedList(List<String> currentList, Map<Integer, String> confirmedLetters);

    List<String> getReducedListByIndex(List<String> currentList, Map<Integer, List<String>> confirmedNotIndex);

}
