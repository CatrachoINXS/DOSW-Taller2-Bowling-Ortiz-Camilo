package edu.eci.dosw.bowling;

public class Frame {
    private int pins;
    private int attempts;
    private boolean bonus = false;
    private FrameType frameType;

    public Frame() {
        this.pins = 0;
        this.attempts = 0;
        this.frameType = FrameType.NORMAL;
    }

    public void addPins(int newPins) {
        if (pins + newPins > 10 && !frameType.equals(FrameType.TENTH)) {
            throw new IllegalArgumentException("La suma de pinos en el frame no puede ser más de 10");
        }
        pins += newPins;
        attempts++;

        if (!frameType.equals(FrameType.TENTH)) {
            this.frameType = validateFrameType();
        } else {
            if ((pins == 10 && attempts == 1) || (pins == 10 && attempts == 2)) {
                bonus = true;
            }
        }
    }

    private FrameType validateFrameType() {
        if (pins == 10 && attempts == 1) {
            return FrameType.STRIKE;
        } else if (pins == 10 && attempts == 2) {
            return FrameType.SPARE;
        }
        return FrameType.NORMAL;
    }

    public int getPins() {
        return pins;
    }

    public boolean isComplete() {
        if (frameType.equals(FrameType.TENTH)) {
            return bonus ? attempts == 3 : attempts == 2;
        }
        return pins == 10 || attempts == 2;
    }

    public FrameType getFrameType() {
        return frameType;
    }

    public void isTenthFrame() {
        this.frameType = FrameType.TENTH;
    }
    
}
