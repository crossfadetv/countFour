package countFour.view;

import countFour.Controller;
import countFour.model.Game;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class PlayScreen extends GridPane {
    //TODO: discuss where to define.
    private static final int COLUMNS = Game.COLUMNS;
    private static final int ROWS = Game.ROWS;
    private static final int FIELDSIZE = Game.FIELDSIZE;

    private Stage primaryStage;
    private Pane stonePane = new Pane();
    private Controller controller;

//TODO: define controller

    public PlayScreen(Controller controller, Stage primaryStage){
        super();
        this.controller = controller;
        this.primaryStage = primaryStage;
    }


    public Parent buildPlayScreen(){
        this.getChildren().add(stonePane);
        this.setPrefSize(700, 700);

        for (int x = 1; x <= COLUMNS; x++) {
            Polygon positionArrow = createArrow();
            positionArrow.setTranslateX((x * FIELDSIZE) - 80);
            positionArrow.setOnMouseEntered(event -> positionArrow.setCursor(Cursor.HAND));
            final int column = x;
            positionArrow.setOnMouseClicked(event ->  makeMove(column));
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

    public void showScreen() {
        Scene scene = new Scene(buildPlayScreen());
        scene.setFill(null);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void makeMove(int column){
        try {
            stonePane.getChildren().add(controller.handlePlayMove(column));
        }
        catch(NullPointerException e){
            //TODO: introduce error handling
        }
    }





}
