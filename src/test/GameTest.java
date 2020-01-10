package test;

import countFour.model.Game;
import countFour.model.Player;
import countFour.model.Stone;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {


    @Before
    public void setUp() {

    }
    //funktioniert noch nicht
    @Test
    public void testCheckForWinners() {
        Game game = new Game();
        game.startGame("1", "2");
        Stone[][] stoneContainerGrid = new Stone[Game.COLUMNS][Game.ROWS];
        Player testRedPlayer = new Player("redPlayerName", Color.RED, true);
        stoneContainerGrid [3][2] = new Stone(testRedPlayer);
        stoneContainerGrid [4][2] = new Stone(testRedPlayer);
        stoneContainerGrid [5][2] = new Stone(testRedPlayer);
        stoneContainerGrid [6][2] = new Stone(testRedPlayer);
        game.checkForWinner(testRedPlayer,3,2);
        boolean status = game.getHasGameEnded();
        System.out.println(stoneContainerGrid);
        assertEquals(true,status);


    }

}