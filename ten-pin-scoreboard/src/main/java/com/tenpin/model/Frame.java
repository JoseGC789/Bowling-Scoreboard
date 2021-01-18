package com.tenpin.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@ToString
@SuperBuilder
@EqualsAndHashCode
public abstract class Frame {

    protected final Roll first;
    protected final Roll second;
    protected Frame next;

    public Frame(Roll first, Roll second, Frame next) {
        this.first = first;
        this.second = second;
        this.next = next;
    }

    protected boolean hasNext(){
        return next != null;
    }

    public abstract Integer scoreForSpare();
    public abstract Integer scoreForStrike();
    public abstract Integer score();
    public abstract String mark();
}
