/**
 * Implements tests for Stone class
 *
 *
 * @author  Markus Steiner
 * @version 1.0
 * @since   2019-12-01
 */
package test;

import countFour.model.Game;
import countFour.model.SavedGame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestSavedGame {
    private String redPlayer;
    private String yellowPlayer;
    private static final String PATH = "testSavedGame.ser";
    private ArrayList<Integer> turns = new ArrayList<>();
    private Game game = new Game();
    private SavedGame oldSavedGame = new SavedGame();

    @Before
    public void setUp() {
        redPlayer = "Frau Rot";
        yellowPlayer = "Herr Gelb";
        oldSavedGame.loadGame(SavedGame.getPATH());
    }
    @Test
    public void testSerializing() {
        game.startGame(redPlayer,yellowPlayer);
        assertThat(game.getSavedGame().getYellowPlayerName(),is(yellowPlayer));
        assertThat(game.getSavedGame().getRedPlayerName(),is(redPlayer));
        assertEquals(game.getSavedGame().getTurns(),turns);
        game.playMove(1);
        game.getSavedGame().saveGame(PATH);
        game.getSavedGame().loadGame(PATH);
        assertNotEquals(game.getSavedGame().getTurns(), turns);
        game.getSavedGame().addTurn(6);
        game.getSavedGame().saveGame(PATH);
        game.getSavedGame().loadGame(PATH);
        turns.add(1);
        turns.add(6);
        assertEquals(game.getSavedGame().getTurns(),turns);
    }
    /**
     * Restore the original SavedGame object which was overwritten in the testSerializing() method
     * by the playMove() method.
     */
    @After
    public void restoreOriginalSavedGame() {
        oldSavedGame.saveGame(SavedGame.getPATH());
    }
}
