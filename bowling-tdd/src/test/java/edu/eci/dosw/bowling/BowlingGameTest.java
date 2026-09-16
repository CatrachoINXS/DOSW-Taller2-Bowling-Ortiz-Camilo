package edu.eci.dosw.bowling;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    @DisplayName("Un Spare en el frame 1 añade puntos extra")
    void aSpareInTheFirstFrameAddsThePinsOfTheFirstRollInTheNextFrame() {
        BowlingGame game = new BowlingGame();
        game.roll(5);
        game.roll(5);
        game.roll(3);
        game.roll(5);
        rollMany(game, 16, 0);

        assertEquals(21, game.score());
        assertTrue(game.isComplete());
    }

    @Test
    @DisplayName("Un Strike en el frame 1 añade puntos extra")
    void aStrikeInTheFirstFrameAddsThePinsOfTheNextFrame() {
        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(4);
        game.roll(3);
        rollMany(game, 16, 0);

        assertEquals(24, game.score());
        assertTrue(game.isComplete());
    }

    @Test
    @DisplayName("Dos strikes y un roll(5) suman correctamente")
    void twoStikesAndThenARollSumCorrectly() {
        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(10);
        game.roll(5);
        rollMany(game, 15, 0);

        assertEquals(45, game.score());
        assertTrue(game.isComplete());
    }

    @Test
    @DisplayName("Todos spare y el último roll(5) dan 150 puntos")
    void rollAllSparesAndThenRollFiveShouldSumCorrectly() {
        BowlingGame game = new BowlingGame();
        rollAllSpares(game, 5);

        assertEquals(150, game.score());
    }

    @Test
    @DisplayName("Un juego perfecto retorna el máximo puntaje. 300 puntos")
    void rollPerfectGameShouldSumCorrectly() {
        BowlingGame game = new BowlingGame();
        rollPerfectGame(game);

        assertEquals(300, game.score());
    }

    @Test
    @DisplayName("score() antes de completar el juego lanza excepcion")
    void scoreBeforeGameIsComplete_throwsException() {
        BowlingGame game = new BowlingGame();
        game.roll(6);
        
        assertThrows(IllegalStateException.class, () -> game.score());
    }

    @Test
    @DisplayName("isComplete() al inicio del juego retorna false")
    void isCompleteAtTheBegginingOfTheGameReturnsFalse() {
        BowlingGame game = new BowlingGame();
        assertFalse(game.isComplete());
    }

    @Test
    @DisplayName("isComplete() despues de nueve frames completos retorna false")
    void isCompleteAfterPlayedNineFramesReturnsFalse() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 18, 3);
        assertFalse(game.isComplete());
    }

    @Test
    @DisplayName("isComplete() despues de diez frames normales retorna true")
    void isCompleteAfterPlayedTenNormalFramesReturnsTrue() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 3);
        assertTrue(game.isComplete());
    }

    @Test
    @DisplayName("spare en el frame 10 y tiro bonus completa el juego")
    void isCompleteAfterSpareInTenthFrameAndBonusReturnsTrue() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 18, 3);
        game.roll(5);
        game.roll(5);
        game.roll(9);
        assertTrue(game.isComplete());
    }

    @Test
    @DisplayName("strike en el frame 10 y dos tiros bonus completa el juego")
    void isCompleteAfterStrikeInTenthFrameAndDoubleBonusReturnsTrue() {
        BowlingGame game = new BowlingGame();
        rollMany(game, 18, 3);
        game.roll(10);
        game.roll(5);
        game.roll(9);
        assertTrue(game.isComplete());
    }

    @Test
    @DisplayName("isComplete() despues de un juego perfecto retorna true")
    void isCompleteAfterAPerfectGameReturnsTrue() {
        BowlingGame game = new BowlingGame();
        rollPerfectGame(game);
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
