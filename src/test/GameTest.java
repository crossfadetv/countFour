package test;

import countFour.model.Game;
import countFour.model.Player;
import countFour.model.Stone;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class GameTest {

    private Game game = new Game();
    private ArrayList<Player> players;
    private Stone[][] stoneContainerGrid = new Stone[Game.COLUMNS][Game.ROWS];
    private boolean hasGameEnded = false;
    private boolean isDraw = false;
    private Player testRedPlayer;
    private Player testYellowPlayer;
    private Stone stone1;
    private Stone stone2;
    private Stone stone3;
    private Stone stone4;


    @Before
    public void setUp() {
        testRedPlayer = new Player("Frau Rot", Color.RED,true);
        testYellowPlayer = new Player("Herr Gelb", Color.YELLOW, false);
    }

    @Test
    public void testStartGame() {
        game.startGame("Bibi", "Tina");
        players = game.getPlayers();
        assertThat(2, is(game.getPlayers().size()));
        Player redPlayer = players.get(0);
        Player yellowPlayer = players.get(1);
        assertThat("Bibi", is(redPlayer.getName()));
        assertThat("Tina", is(yellowPlayer.getName()));
        assertThat(Color.RED, is(redPlayer.getColor()));
        assertThat(Color.YELLOW,is(yellowPlayer.getColor()));
        assertTrue(redPlayer.onTurn());
        assertFalse(yellowPlayer.onTurn());
    }

    @Test
    public void testHorizontalWin() {
        stoneContainerGrid [3][2] = new Stone(testRedPlayer);
        stoneContainerGrid [4][2] = new Stone(testRedPlayer);
        stoneContainerGrid [5][2] = new Stone(testRedPlayer);
        stoneContainerGrid [6][2] = new Stone(testRedPlayer);
        players = new ArrayList<>();
        players.add(testRedPlayer);
        players.add(testYellowPlayer);
        game.checkForWinner(testRedPlayer,3,2);
        assertTrue(game.getHasGameEnded());
    }




    //funktioniert noch nicht
    /*@Test
    public void testCheckForWinners() {
        game.startGame("1", "2");
        Player testRedPlayer = game.getPlayerOnTurn();
        Stone[][] stoneContainerGrid = new Stone[Game.COLUMNS][Game.ROWS];
        stoneContainerGrid [3][2] = new Stone(testRedPlayer);
        stoneContainerGrid [4][2] = new Stone(testRedPlayer);
        stoneContainerGrid [5][2] = new Stone(testRedPlayer);
        stoneContainerGrid [6][2] = new Stone(testRedPlayer);
        game.checkForWinner(testRedPlayer,3,2);
        boolean status = game.getHasGameEnded();
        System.out.println(testRedPlayer);
        assertEquals(true,status);


    }*/

}