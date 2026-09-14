package edu.eci.dosw.bowling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    @Test
    @DisplayName("roll(0) No lanza excepción. El frame registra 0 pinos.")
    void shouldRollZeroPins() {
        // Arrange
        BowlingGame game = new BowlingGame();
        // Act & Assert
        assertDoesNotThrow(() -> game.roll(0));
    }

    @Test
    @DisplayName("roll(-1) lanza IllegalArgumentException")
    void rollNegativePins_throwsException() {
        // Arrange
        BowlingGame game = new BowlingGame();
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> game.roll(-1));
    }

    @Test
    @DisplayName("roll(11) lanza IllegalArgumentException")
    void rollGreaterThanTenPins_throwsException() {
        BowlingGame game = new BowlingGame();
        assertThrows(IllegalArgumentException.class, () -> game.roll(11));
    }

}
