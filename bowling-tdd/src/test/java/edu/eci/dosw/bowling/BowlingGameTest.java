package edu.eci.dosw.bowling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        for (int i = 0; i < 12; i++) {
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

    @Test
    @DisplayName("roll(5) dos veces detecta spare en un frame")
    void rollFivePinsTwiceDetectsSpareInAFrame() {
        BowlingGame game = new BowlingGame();
        game.roll(5);
        game.roll(5);
        
        assertEquals(FrameType.SPARE, game.getFrames().get(0).getFrameType());
    }

    @Test
    @DisplayName("El décimo roll con Strike acepta tres tiradas")
    void tenthFrameWithStrikeShouldAcceptThreeRolls() {
        BowlingGame game = new BowlingGame();
        for (int i = 0; i < 9; i++) {
            game.roll(0);
            game.roll(0);
        }

        assertDoesNotThrow(() -> {
            game.roll(10);
            game.roll(10);
            game.roll(10);
        });
    }

    @Test
    @DisplayName("Un juego con todos los tiros a cero retorna 0")
    void gameWithZeroPinsShouldReturnScoreZero() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 0);
        int score = game.score();

        assertEquals(0, score);
        assertTrue(game.isComplete());
    }

    @Test
    @DisplayName("El puntaje de un juego sin stikes ni spares es la suma de los pinos")
    void gameWithoutStrikeAndSparesEqualsThePinsSum() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 4);

        assertEquals(game.score(), 80);
        assertTrue(game.isComplete());
    }




    private void rollMany(BowlingGame game, int times, int pins) {
        for (int i = 0; i < times; i++) game.roll(pins);
    }
    // Juego perfecto: 12 strikes
    private void rollPerfectGame(BowlingGame game) {
        for (int i = 0; i < 12; i++) game.roll(10);
    }
    // Todos spares
    private void rollAllSpares(BowlingGame game, int lastBonus) {
        for (int i = 0; i < 10; i++) { game.roll(5); game.roll(5); }
        game.roll(lastBonus);
    }

}
