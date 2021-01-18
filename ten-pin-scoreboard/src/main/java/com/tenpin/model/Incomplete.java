package com.tenpin.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class Incomplete extends Frame{

    public Incomplete(@NonNull Roll first) {
        super(first, null, null);
    }

    @Override
    public Integer score() {
        return null;
    }

    @Override
    public Integer scoreForSpare() {
        return first.getScore();
    }

    @Override
    public Integer scoreForStrike() {
        return null;
    }

    @Override
    public String mark() {
        return "\t" + first.getScore();
    }
}
