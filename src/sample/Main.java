package sample;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;


public class Main extends Application {
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = createScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene createScene() {
        Scene scene = new Scene(createBoard());
        scene.setFill(Color.NAVY);
        return scene;
    }

    private Parent createBoard() {
        Pane root = new GridPane();
        root.setPrefSize(700, 700);

        for (int x = 1; x <= 7; x++) {
            Polygon positionArrow = createArrow();
            positionArrow.setTranslateX((x *100)-80);
            root.getChildren().add(positionArrow);
            for (int y = 1; y <= 6; y++) {
                PositionCircle positionCircle = new PositionCircle();
                positionCircle.setTranslateX((x * 100) - 90);
                positionCircle.setTranslateY((y * 100));
                root.getChildren().add(positionCircle);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Polygon createArrow(){
        double points[]={ 60,90,
                30,50,
                45,50,
                45,30,
                75,30,
                75,50,
                90,50};

        Polygon arrow = new Polygon(points);
        arrow.setFill(Color.GREEN);
        arrow.setOnMouseEntered(event -> arrow.setCursor(Cursor.HAND));
        arrow.setOnMouseClicked(event -> System.out.println("clicked"));
        return arrow;
    }
}