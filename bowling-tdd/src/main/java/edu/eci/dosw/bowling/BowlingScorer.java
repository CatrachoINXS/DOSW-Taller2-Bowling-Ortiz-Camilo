package edu.eci.dosw.bowling;

import java.util.List;
import java.util.stream.IntStream;

public class BowlingScorer {

    public static int calculate(List<Frame> frames) {
        return IntStream.range(0, frames.size())
            .map(i -> {
                Frame frame = frames.get(i);
                int score = frame.getPins();
                if (frame.getFrameType().equals(FrameType.SPARE)) {
                    score += frames.get(i + 1).getFirstRoll();
                } else if (frame.getFrameType().equals(FrameType.STRIKE)) {
                    Frame nextFrame = frames.get(i + 1);
                    score += (nextFrame.getFirstRoll() + nextFrame.getSecondRoll());
                }

                return score;
            })
            .sum();
    }
}
