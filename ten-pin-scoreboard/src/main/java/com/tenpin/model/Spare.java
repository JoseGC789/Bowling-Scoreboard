package com.tenpin.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class Spare extends Frame {

    public Spare(@NonNull Roll first, @NonNull Roll second, Frame next) {
        super(first, second, next);
    }

    @Override
    public Integer score() {
        return hasNext() && next.scoreForSpare() != null ? 10 + next.scoreForSpare() : null;
    }

    @Override
    public Integer scoreForSpare() {
        return first.getScore();
    }

    @Override
    public Integer scoreForStrike() {
        return 10;
    }

    @Override
    public String mark() {
        return "\t" + (first.isF() ? "F": first.getScore()) + "\t/"  + (hasNext() ? next.mark() : "");
    }
}
