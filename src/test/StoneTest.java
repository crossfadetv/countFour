package test;

import countFour.model.Player;
import countFour.model.Stone;
import javafx.scene.paint.Color;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class StoneTest {
    private Stone stone;
    private Player player = new Player("Ich", Color.RED,true);
    //private Color color;

    @Before
    public void setUp() {
        stone = new Stone(player);
    }

    @Test
    public void testGetColor() {
        Color color = stone.getColor();
        assertThat(color,is(Color.RED));

    }

}
