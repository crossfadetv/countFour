package countFour.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Stone extends Circle {
    private Color color;
    //private Player player;


public Stone(Player player){
    this.color = player.getColor();
   // this.player = player;
    setFill(color);
    this.setRadius(40.0);
}

public Color getColor(){
    return color;
}


}
