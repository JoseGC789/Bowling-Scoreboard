package com.tenpin;

import com.tenpin.model.Scoreboard;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;

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
        Collection<List<Scoreboard>> collect = scoreboard.stream().collect(collectingAndThen(groupingBy(Scoreboard::getName), Map::values));
        for (List<Scoreboard> scoreboard: collect) {
            System.out.println(scoreboard);
        }
    }

}
