package com.josegc789;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class ApplicationStart implements CommandLineRunner {

    private final List<Score> scores;

    public ApplicationStart(List<Score> scores) {
        this.scores = scores;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

    @Override
    public void run(String... args){
        for (Score score: scores) {
            System.out.println(score);
        }
    }

    @ToString
    public static class Score {
        @JsonProperty("name")
        private final String name;
        @JsonProperty("score")
        private final String score;

        @JsonCreator
        public Score(@JsonProperty("name") String name, @JsonProperty("score") String score) {
            this.name = name;
            this.score = score;
        }
    }
}
