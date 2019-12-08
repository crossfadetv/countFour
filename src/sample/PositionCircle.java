package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class PositionCircle extends StackPane {
    public PositionCircle(){
        Circle circle = new Circle(40);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(circle);

    }

}
