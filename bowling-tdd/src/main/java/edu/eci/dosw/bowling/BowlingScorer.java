package edu.eci.dosw.bowling;

import java.util.List;

public class BowlingScorer {

    public static int calculate(List<Frame> frames) {
        int score = frames.stream().mapToInt(Frame::getPins).sum();
        return score;
    }
}
