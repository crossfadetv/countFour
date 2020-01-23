/**
 * Implements tests for Game class
 *
 *
 * @author  Rahel Krubally, Markus Steiner
 * @version 1.0
 * @since   2019-12-01
 */
package test;

import countFour.model.Game;
import countFour.model.Player;
import countFour.model.Stone;
import javafx.scene.paint.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;


public class GameTest {

    private Game game = new Game();
    private Player testRedPlayer;
    private Player testYellowPlayer;


    @Before
    public void setUp() {
        testRedPlayer = new Player("Frau Rot", Color.RED, true);
        testYellowPlayer = new Player("Herr Gelb", Color.YELLOW, false);
    }

    @Test
    public void testStartGame() {
        game.startGame("Bibi", "Tina");
        ArrayList<Player> players = game.getPlayers();
        assertThat(2, is(game.getPlayers().size()));
        Player redPlayer = players.get(0);
        Player yellowPlayer = players.get(1);
        assertThat("Bibi", is(redPlayer.getName()));
        assertThat("Tina", is(yellowPlayer.getName()));
        assertThat(Color.RED, is(redPlayer.getColor()));
        assertThat(Color.YELLOW, is(yellowPlayer.getColor()));
        assertTrue(redPlayer.onTurn());
        assertFalse(yellowPlayer.onTurn());
    }

    @Test
    public void testHorizontalWin() {
        game.startGame("Frau Rot", "Herr Gelb");
        game.playMove(0);
        game.playMove(6);
        game.playMove(1);
        game.playMove(5);
        game.playMove(2);
        game.playMove(4);
        game.playMove(3);

        game.checkForWinner(testRedPlayer, 3, 2);
        assertTrue(game.getHasGameEnded());
        game.identifyWinningStones();
        assertSame(game.getStoneContainerGrid()[3][5].getFill(), Color.DARKRED);

    }

    @Test
    public void testVerticalWin() {
        game.startGame("Frau Rot", "Herr Gelb");
        game.playMove(1);
        game.playMove(2);
        game.playMove(1);
        game.playMove(2);
        game.playMove(1);
        game.playMove(2);
        game.playMove(1);
        assertTrue(game.getHasGameEnded());
        game.identifyWinningStones();
        assertSame(game.getStoneContainerGrid()[1][2].getFill(), Color.DARKRED);
    }

    @Test
    public void testAscendingDiagonalWin() {
        game.startGame("Frau Rot", "Herr Gelb");
        game.playMove(1);
        game.playMove(2);
        game.playMove(2);
        game.playMove(3);
        game.playMove(3);
        game.playMove(4);
        game.playMove(3);
        game.playMove(4);
        game.playMove(5);
        game.playMove(4);
        game.playMove(4);
        assertTrue(game.getHasGameEnded());
        game.identifyWinningStones();
        assertSame(game.getStoneContainerGrid()[4][2].getFill(), Color.DARKRED);
    }

    @Test
    public void testDescendingDiagonalWin() {
        game.startGame("Frau Rot", "Herr Gelb");
        game.playMove(1);
        game.playMove(1);
        game.playMove(1);
        game.playMove(1);
        game.playMove(2);
        game.playMove(2);
        game.playMove(3);
        game.playMove(2);
        game.playMove(5);
        game.playMove(3);
        game.playMove(6);
        game.playMove(4);
        assertTrue(game.getHasGameEnded());
        game.identifyWinningStones();
        assertSame(game.getStoneContainerGrid()[4][5].getFill(), Color.DARKGOLDENROD);
    }

    @Test
    public void testDraw() {
        game.startGame("Frau Rot", "Herr Gelb");
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 6; y++) {
                game.playMove(x);
            }
        }
        game.playMove(5);
        for (int x = 3; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                game.playMove(x);
            }
        }
        assertTrue(game.getHasGameEnded());
        assertTrue(game.getIsDraw());
    }

    @Test
    public void testLastRowPlayed() {
        game.startGame("Frau Rot", "Herr Gelb");
        game.playMove(0);
        int lastPlayedRow = game.getLastPlayedRow();
        Assert.assertEquals(lastPlayedRow, game.getLastPlayedRow());
    }

    @Test
    public void testGetPlayers() {
        game.startGame("Frau Rot", "Herr Gelb");
        Assert.assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void testChangeTurn() {
        game.startGame("Frau Rot", "Herr Gelb");
        Assert.assertEquals("Frau Rot", game.getPlayerOnTurn().getName());
        game.changePlayerTurn();
        Assert.assertEquals("Herr Gelb", game.getPlayerOnTurn().getName());
    }

    @Test
    public void testIdentifyWinningStones(){
        game.startGame("Frau Rot", "Herr Gelb");
        game.playMove(1);
        game.playMove(1);
        game.playMove(1);
        game.playMove(1);
        game.playMove(2);
        game.playMove(2);
        game.playMove(3);
        game.playMove(2);
        game.playMove(5);
        game.playMove(3);
        game.playMove(6);
        game.playMove(4);
        assertTrue(game.getHasGameEnded());
        game.identifyWinningStones();
        assertSame(game.getStoneContainerGrid()[4][5].getFill(), Color.DARKGOLDENROD);
    }

    }
