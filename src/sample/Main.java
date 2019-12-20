package sample;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.io.FileOutputStream;
import java.lang.reflect.Field;


public class Main extends Application {
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    private static final int FIELDSIZE = 100;
    private Stone[][] stoneContainerGrid = new Stone[COLUMNS][ROWS];
    private Pane stonePane = new Pane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = createScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene createScene() {
        Scene scene = new Scene(createBoard());
        scene.setFill(null);
        return scene;
    }

    private Parent createBoard() {
        Pane root = new GridPane();
        root.getChildren().add(stonePane);
        root.setPrefSize(700, 700);

        for (int x = 1; x <= COLUMNS; x++) {
            Polygon positionArrow = createArrow();
            positionArrow.setTranslateX((x * FIELDSIZE) - 80);
            positionArrow.setOnMouseEntered(event -> positionArrow.setCursor(Cursor.HAND));
            final int column = x;
            positionArrow.setOnMouseClicked(event -> throwStone(column, new Player()));
            // positionArrow.setOnMouseClicked(event -> System.out.println(column));
            root.getChildren().add(positionArrow);
            for (int y = 1; y <= ROWS; y++) {
                Rectangle rectangle = new Rectangle((x - 1) * FIELDSIZE, y * FIELDSIZE, 100, 100);
                rectangle.setFill(Color.NAVY);
                Circle circle = new Circle((x - 1) * FIELDSIZE + FIELDSIZE / 2, y * FIELDSIZE + FIELDSIZE / 2, FIELDSIZE / 2 - 10);
                Shape field = Shape.subtract(rectangle, circle);
                field.setFill(Color.NAVY);
                field.setTranslateX(((x - 1) * 100));
                field.setTranslateY((y * 100));
                root.getChildren().add(field);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Polygon createArrow() {
        double[] points = {60, 90, 30, 50, 45, 50, 45, 30, 75, 30, 75, 50, 90, 50};
        Polygon arrow = new Polygon(points);
        arrow.setFill(Color.GREEN);
        return arrow;
    }

    private void throwStone(int column, Player player) {
        int row = ROWS-1;
        column = column -1;
        Stone stone = player.playStone();
        stoneContainerGrid[column][row] = stone;
        stone.setTranslateX(column*FIELDSIZE + 50); //hässlich mit dem 50
        stone.setTranslateY(row*FIELDSIZE + 150); //hässlich mit dem 150
        stonePane.getChildren().add(stone);
    }
}