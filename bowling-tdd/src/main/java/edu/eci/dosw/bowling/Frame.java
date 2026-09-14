package edu.eci.dosw.bowling;

public class Frame {
    private int pins;
    private int attempts;
    private FrameType frameType;

    public Frame() {
        this.pins = 0;
        this.attempts = 0;
        this.frameType = FrameType.NORMAL;
    }

    public void addPins(int newPins) {
        if (pins + newPins > 10) {
            throw new IllegalArgumentException("La suma de pinos en el frame no puede ser más de 10");
        }
        pins += newPins;
        attempts++;

        if (pins == 10 && attempts == 1) {
            this.frameType = FrameType.STRIKE;
        } else if (pins == 10 && attempts == 2) {
            this.frameType = FrameType.SPARE;
        }
    }

    public int getPins() {
        return pins;
    }

    public boolean isComplete() {
        return pins == 10 || attempts == 2;
    }

    public FrameType getFrameType() {
        return frameType;
    }

}
