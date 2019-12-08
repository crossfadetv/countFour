package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(createScene());
        primaryStage.show();


    }

    private Scene createScene() {
        Scene scene = new Scene(createBoard());
        scene.setFill(Color.NAVY);
        return scene;
    }

    private Parent createBoard() {
        Pane root = new AnchorPane();
        root.setPrefSize(700, 600);



        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 6; j++) {
                PositionCircle positionCircle = new PositionCircle();
                positionCircle.setTranslateX((i * 100) - 90);
                positionCircle.setTranslateY((j * 100) - 90);
                root.getChildren().add(positionCircle);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}