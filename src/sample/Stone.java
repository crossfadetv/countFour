package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Stone extends Circle {
    private Color color = Color.RED;

public Stone(Color color){
    this.color = color;
    this.setRadius(40.0);
}


}
