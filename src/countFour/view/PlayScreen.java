package countFour.view;

import countFour.Controller;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PlayScreen extends GridPane {

    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    private static final int FIELDSIZE = 100;
    private Pane stonePane = new Pane();
    private Controller controller;

//TODO: define controller

    public PlayScreen(){
        super();
    }


    public Parent buildPlayScreen(){
        this.getChildren().add(stonePane);
        this.setPrefSize(700, 700);

        for (int x = 1; x <= COLUMNS; x++) {
            Polygon positionArrow = createArrow();
            positionArrow.setTranslateX((x * FIELDSIZE) - 80);
            positionArrow.setOnMouseEntered(event -> positionArrow.setCursor(Cursor.HAND));
            final int column = x;
            positionArrow.setOnMouseClicked(event ->  stonePane.getChildren().add(controller.handleThrowStone(column)));
            this.getChildren().add(positionArrow);
            for (int y = 1; y <= ROWS; y++) {
                Rectangle rectangle = new Rectangle((x - 1) * FIELDSIZE, y * FIELDSIZE, 100, 100);
                rectangle.setFill(Color.NAVY);
                Circle circle = new Circle((x - 1) * FIELDSIZE + FIELDSIZE / 2, y * FIELDSIZE + FIELDSIZE / 2, FIELDSIZE / 2 - 10);
                Shape field = Shape.subtract(rectangle, circle);
                field.setFill(Color.NAVY);
                field.setTranslateX(((x - 1) * 100));
                field.setTranslateY((y * 100));
                this.getChildren().add(field);
            }
        }
        return this;
    }

    private Polygon createArrow() {
        double[] points = {60, 90, 30, 50, 45, 50, 45, 30, 75, 30, 75, 50, 90, 50};
        Polygon arrow = new Polygon(points);
        arrow.setFill(Color.GREEN);
        return arrow;
    }






}
