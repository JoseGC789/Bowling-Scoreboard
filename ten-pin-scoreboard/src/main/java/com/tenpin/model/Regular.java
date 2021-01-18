package com.tenpin.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class Regular extends Frame{

    public Regular(@NonNull Roll first, @NonNull Roll second, Frame next) {
        super(first, second, next);
        if (first.getScore() + second.getScore() > 9){
            throw new IllegalStateException("You can't sum more than 10 points in single frame!");
        }
    }

    @Override
    public Integer score() {
        return super.first.getScore() + super.second.getScore();
    }

    @Override
    public Integer scoreForSpare() {
        return first.getScore();
    }

    @Override
    public Integer scoreForStrike() {
        return score();
    }

    @Override
    public String mark() {
        return "\t" + (first.isF() ? "F": first.getScore()) + "\t" + (second.isF() ? "F": second.getScore()) + (hasNext() ? next.mark() : "");
    }
}
