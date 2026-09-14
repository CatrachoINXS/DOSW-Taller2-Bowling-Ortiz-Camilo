package edu.eci.dosw.bowling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    @DisplayName("roll(7) y roll (6) lanza IllegalArgumentException en el segundo tiro")
    void scoreGreaterThanTenInOneFrame_throwsException() {
        BowlingGame game = new BowlingGame();
        game.roll(7);
        assertThrows(IllegalArgumentException.class, () -> game.roll(6));
    }

    @Test
    @DisplayName("roll() cuando el juego está completo lanza IllegalStateException")
    void rollWhenGameIsComplete_throwsException() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 10; i++) {
            game.roll(10);
        }
        
        assertThrows(IllegalStateException.class, () -> game.roll(6));
    }

    @Test
    @DisplayName("roll(10) detecta strike en un frame")
    void rollTenPinsDetectsStrikeInAFrame() {
        BowlingGame game = new BowlingGame();
        game.roll(10);
        
        assertEquals(FrameType.STRIKE, game.getFrames().get(0).getFrameType());
    }

}
