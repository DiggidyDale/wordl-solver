package com.example.wordlsolver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.PromptProvider;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

@SpringBootApplication
public class WordlSolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordlSolverApplication.class, args);
    }

   @Bean
    public PromptProvider promptProvider(){
        return () -> new AttributedString("wordl-shell:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));

   }
}
