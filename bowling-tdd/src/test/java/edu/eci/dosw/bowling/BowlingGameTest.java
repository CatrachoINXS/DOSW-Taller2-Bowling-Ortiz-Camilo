package edu.eci.dosw.bowling;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

    @Test
    @DisplayName("roll(-1) lanza IllegalArgumentException")
    void rollNegativePins_throwsException() {
        // Arrange
        BowlingGame game = new BowlingGame();
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> game.roll(-1));
    }
}
