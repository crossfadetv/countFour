package countFour.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Stone extends Circle {
    private Color color;

    /**
     * @param player the player to which the Stone belongs
     */
    public Stone(Player player) {
        this.color = player.getColor();
        setFill(color);
        this.setRadius(40.0);
    }

    /**
     * @return the color of the stone
     */
    public Color getColor() {
        return color;
    }


}
