package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor de un juego de Bowling para un jugador.
 * Un juego tiene exactamente 10 frames.
 */
public class BowlingGame {

    private final List<Frame> frames;
    private int currentFrame;

    public BowlingGame() {
        this.frames = new ArrayList<>();
        this.currentFrame = -1;
    }

    /** Registra pinos derribados. Lanza IllegalArgumentException si pines < 0 o > 10.
     * Lanza IllegalStateException si el juego ya termino. */
    public void roll(int pins) {
        if (isComplete()) {
            throw new IllegalStateException("No se puede hacer roll cuando el juego ya terminó");
        }

        if (pins < 0) {
            throw new IllegalArgumentException("Los valores menores a cero, no son válidos");
        } else if (pins > 10) {
            throw new IllegalArgumentException("Los valores mayores a 10 no son válidos");
        }

        if (frames.isEmpty() || frames.get(currentFrame).isComplete()) {
            frames.add(new Frame());
            currentFrame++;
            if (frames.size() == 10) {
                frames.get(currentFrame).isTenthFrame();
            }
        }
        frames.get(currentFrame).addPins(pins);
    }

    /** Puntaje total. Lanza IllegalStateException si el juego no esta completo. */
    public int score() {
        int score = BowlingScorer.calculate(frames);
        return score;
    }

    /** true cuando los 10 frames han sido completados. */
    public boolean isComplete() {
        return this.frames.size() == 10 && this.frames.get(9).isComplete();
    }

    public List<Frame> getFrames() { return List.copyOf(frames); }
}