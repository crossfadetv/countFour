package test;

import countFour.model.Player;
import countFour.model.Stone;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player = new Player ("Max Mustermann", Color.RED, true);
    @Test
    public void testGetName() {
        String name = player.getName();
        assertEquals("Max Mustermann", name);
    }
    @Test
    public void testGetColor() {
        Color color = player.getColor();
        assertEquals(Color.RED, color);
    }
    @Test
    public void testCountStones() {
        int stoneAmount = player.countStones();
        assertEquals(21,stoneAmount);
    }
    @Test
    public void testFillStones() {
        assertEquals(21, player.countStones());
    }
    @Test
    public void testPlayStone() {
        Stone stone = player.playStone();
        int amount= player.countStones();
        assertEquals(20,amount);
    }
}