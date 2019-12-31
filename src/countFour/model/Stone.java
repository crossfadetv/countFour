package countFour.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Stone extends Circle {
    private Color color;

public Stone(Color color){
    this.color = color;
    setFill(color);
    this.setRadius(40.0);
}

public Color getColor(){
    return color;
}
}
