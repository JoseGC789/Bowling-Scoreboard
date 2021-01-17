package com.tenpin;

import com.tenpin.model.Scoreboard;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class ApplicationStart implements CommandLineRunner {

    private final List<Scoreboard> scoreboard;

    public ApplicationStart(List<Scoreboard> scoreboard) {
        this.scoreboard = scoreboard;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

    @Override
    public void run(String... args){
        scoreboard.forEach(System.out::println);
    }

}
