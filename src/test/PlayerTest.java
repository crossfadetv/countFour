package test;

import countFour.model.Player;
import countFour.model.Stone;
import javafx.scene.paint.Color;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player = new Player ("Max Mustermann", Color.RED, true);
    @Test
    public void testGetName() {
        String name = player.getName();
        assertThat("Max Mustermann", is(name));
    }
    @Test
    public void testGetColor() {
        Color color = player.getColor();
        assertThat(Color.RED, is(color));
    }
    @Test
    public void testCountStones() {
        int stoneAmount = player.countStones();
        assertThat(21,is(stoneAmount));
    }
    @Test
    public void testSetName(){
        player.setName("Other Name");
        assertThat("Other Name",is(player.getName()));
    }
    @Test
    public void testFillStones() {
        assertThat(21, is(player.countStones()));
    }
    @Test
    public void testPlayStone() {
        Stone testStone = (Stone) player.getStones().get(20);
        Stone stone = player.playStone();
        int amount= player.countStones();
        assertThat(20,is(amount));
        assertThat(testStone,is(stone));
    }
}