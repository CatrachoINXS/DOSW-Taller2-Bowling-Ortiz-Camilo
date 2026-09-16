package edu.eci.dosw.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingScorerTest {
    
    @Test
    @DisplayName("El puntaje de un juego normal se calcula correctamente")
    void shouldCalculateTheScoreOfANormalGameCorrectly() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 3);

        assertEquals(60, game.score());
    }

    @Test
    @DisplayName("El puntaje de un juego con strikes se calcula correctamente")
    void shouldCalculateTheScoreOfAGameWithStrikesCorrectly() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 5, 10);
        rollMany(game, 10, 4);

        assertEquals(172, game.score());
    }

    private void rollMany(BowlingGame game, int times, int pins) {
        for (int i = 0; i < times; i++) game.roll(pins);
    }
}
