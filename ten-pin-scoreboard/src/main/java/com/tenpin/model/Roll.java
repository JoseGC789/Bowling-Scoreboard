package com.tenpin.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@ToString
public class Roll {
    @JsonProperty("name")
    @NotEmpty(message = "You forgot to fill out a player's name")
    private final String name;
    @JsonProperty("score")
    private final Integer score;
    private final boolean f;

    @JsonCreator
    public Roll(@JsonProperty("name") String name,
                @JsonProperty("score") @Pattern(message = "%s but it should have been between 0-10, X, /, F or -", regexp = "([0-9]|10|X|x|F|f|-|/)") String score) {
        this.name = name;
        this.score = score.matches("([Ff\\-])") ? 0 : score.matches("([xX/])") ? 10 : Integer.parseInt(score);
        this.f = "F".equalsIgnoreCase(score);
    }

}
