/**
 * This class extends the circle object and represents a play stone. It has a color
 * as property.
 *
 *
 * @author  Rahel Krubally
 * @version 1.0
 * @since   2020-1-19
 */
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
