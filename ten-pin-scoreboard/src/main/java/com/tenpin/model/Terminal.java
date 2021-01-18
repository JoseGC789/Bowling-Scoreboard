package com.tenpin.model;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString
public class Terminal extends Frame{
    private final Frame one;
    private final Frame two;
    private final Frame three;

    public Terminal(Frame one, Frame two, Frame three) {
        super(null, null, null);
        this.one = one;
        this.two = two;
        this.three = three;
    }

    /*
    XI
    SI
    XR +
    XXI
    XXX
    XS
     */

    @Override
    public Integer score() {
        return one.score();
    }

    @Override
    public Integer scoreForSpare() {
        return one.scoreForSpare();
    }

    @Override
    public Integer scoreForStrike() {
        return one.scoreForStrike();
    }

    @Override
    public String mark() {
        return "\t" + String.join("\t", one.mark().replaceAll("\t+", "").split(""));
    }
}
