package com.tenpin.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class Strike extends Frame{

    public Strike(@NonNull Roll second, Frame next) {
        super(null, second, next);
    }

    @Override
    public Integer scoreForSpare() {
        return 10;
    }

    @Override
    public Integer scoreForStrike() {
        return hasNext() && next.scoreForSpare() != null ? 10 + next.scoreForSpare() : null;
    }

    @Override
    public Integer score() {
        return hasNext() && next.scoreForStrike() != null ? 10 + next.scoreForStrike() : null;
    }

    @Override
    public String mark() {
        return "\t\tX" + (hasNext() ? next.mark() : "");
    }
}
