package com.tenpin.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Player {
    private final String name;
    private final List<Scoreboard> scoreboard;

    public Player(String name, List<Scoreboard> scoreboard) {
        this.name = name;
        this.scoreboard = scoreboard;
    }
}
