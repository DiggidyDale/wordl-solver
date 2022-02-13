package com.example.wordlsolver.console;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.wordlsolver.solver.Solver;

/**
 *
 *
 * @author DaleH
 *
 */
@ShellComponent
public class Commands {

    final Solver solver;

    @Autowired
    public Commands(Solver solver) {
        this.solver = solver;
    }

    @ShellMethod("Reset Instance for a new game")
    public String resetGame(){
        this.solver.resetGame();
        return "Game Reset";
    }

    @ShellMethod("Get top 10 best guesses")
    public Map<String, Double> guesses(){

        return this.solver.getTopGuesses();
    }

    @ShellMethod("Enter a new acceptable letters")
    public String good(String newLetters){
        String currentLetters = String.join(",", this.solver.addGoodLetters(newLetters));
        return String.format("New letters: %s; have been added, currently acceptable letters are: %s", newLetters, currentLetters);
    }

    @ShellMethod("Enter new bad letters")
    public String bad(String badLetters){
        String currentBadLetters = String.join(",", this.solver.addBadLetters(badLetters));
        return String.format("Bad letters: %s; have been added, currently unacceptable letters are: %s", badLetters, currentBadLetters);
    }

    @ShellMethod("Enter new confirmed letter")
    public String confirm(int index, String letter){
        Map<Integer, String> confirmedLetters = this.solver.addConfirmedLetters(index, letter);
        return String.format("New confirmed letter: %s at index %d; has been added, current confirmed letters are: %s", letter, index, confirmedLetters);
    }

    @ShellMethod("Enter confirmed bad entries")
    public String confirmb(int index, String letter){
        Map<Integer, List<String>> confirmedLetters = this.solver.addConfirmedBadLetters(index, letter);
        return String.format("New confirmed bad letter: %s at index %d; has been added, current confirmed letters are: %s", letter, index, confirmedLetters);
    }
}
