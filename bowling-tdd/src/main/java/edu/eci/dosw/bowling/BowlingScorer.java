package edu.eci.dosw.bowling;

import java.util.List;
import java.util.stream.IntStream;

public class BowlingScorer {

    private BowlingScorer() {
        
    }

    public static int calculate(List<Frame> frames) {
        return IntStream.range(0, frames.size())
            .map(i -> {
                Frame frame = frames.get(i);
                int score = frame.getPins();
                if (frame.getFrameType().equals(FrameType.SPARE)) {
                    score += frames.get(i + 1).getRolls().getFirst();
                } else if (frame.getFrameType().equals(FrameType.STRIKE)) {
                    score += getNextRolls(frames, i + 1, 2);
                }

                return score;
            })
            .sum();
    }

    private static int getNextRolls(List<Frame> frames, int index, int count) {
        int sum = 0;
        int collected = 0;
        for (int j = index; j < frames.size() && collected < count; j++) {
            List<Integer> rolls = frames.get(j).getRolls();
            for (int roll : rolls) {
                sum += roll;
                collected++;
                if (collected == count) {
                    break;
                }
            }
        }
        return sum;
    }
}
