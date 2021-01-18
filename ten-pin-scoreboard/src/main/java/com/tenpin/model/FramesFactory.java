package com.tenpin.model;

import lombok.val;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FramesFactory {
    private static final int FINAL_FRAME = 9;
    private List<Roll> remaining = new ArrayList<>();

    public List<Frame> build(List<Roll> rolls){
        List<Frame> frames = buildFrames(rolls);
        List<Frame> finalFrames = createFinal(frames);
        buildFrameChain(finalFrames);
        return finalFrames;
    }

    private List<Frame> createFinal(List<Frame> frames) {
        List<Frame> finalFrames;
        if (frames.size() >= FINAL_FRAME){
            finalFrames = frames.stream().limit(9).collect(Collectors.toList());
            List<Frame> last = buildFrames(remaining);
            val builder = Terminal.builder();
            if (last.size() >= 1){
                builder.one(last.get(0));
            }
            if (last.size() >= 2){
                last.get(0).setNext(last.get(1));
                builder.two(last.get(1));
            }
            if (last.size() >= 3){
                last.get(1).setNext(last.get(2));
                builder.three(last.get(2));
            }
            finalFrames.add(builder.build());

        } else {
            finalFrames = frames;
        }
        return finalFrames;
    }

    private void buildFrameChain(List<Frame> finalFrames) {
        Frame next = null;
        for (int i = finalFrames.size() - 1; i >= 0 ; i--) {
            Frame current = finalFrames.get(i);
            current.setNext(next);
            next = current;
        }
    }

    private List<Frame> buildFrames(List<Roll> rolls){
        List<Frame> frames = new ArrayList<>();
        Roll first = null;
        boolean strike = true;
        for (Roll roll : rolls) {
            if (frames.size() >= FINAL_FRAME){
                remaining = rolls.subList(rolls.indexOf(roll), rolls.size()).stream().limit(3).collect(Collectors.toList());
                break;
            }
            if (strike && roll.getScore().equals(10)) {
                frames.add(new Strike(roll, null));
            } else {
                strike = false;
                if (first == null) {
                    first = roll;
                } else {
                    frames.add(first.getScore() + roll.getScore() == 10 ? new Spare(first, roll, null) : new Regular(first, roll, null));
                    strike = true;
                    first = null;
                }
            }
        }
        if (first != null){
            frames.add(new Incomplete(first));
        }
        return frames;
    }
}
