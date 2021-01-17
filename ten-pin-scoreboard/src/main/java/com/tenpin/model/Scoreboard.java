package com.tenpin.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@EqualsAndHashCode
public class Scoreboard {
    @JsonProperty("name")
    @NotEmpty(message = "You forgot to fill out a player's name")
    private final String name;
    @JsonProperty("score")
    @Pattern(message = "%s but it should have been between 0-10, X, /, F or -", regexp = "([0-9]|10|X|x|F|f|-|/)")
    @NotEmpty
    private final String score;

    @JsonCreator
    public Scoreboard(@JsonProperty("name") String name, @JsonProperty("score") String score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Player's scoreboard with his name and score for that throw
     *
     * Ex: Jeff's throw scored a 10
     */
    @Override
    public String toString() {
        return this.name + "'s throw scored a " + this.score;
    }
}
