/*
package countFour.view;

import countFour.Controller;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WinScreen extends Pane {
   private Controller controller;
   private Stage primaryStage;
   private Scene winScene;
   private Label winnerText;


    public WinScreen(Controller controller, Stage primaryStage){
        super();
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.winScene = new Scene(buildWinScreen());
    }

    public void showScreen(String winner){
        winScene.setFill(null);
        winnerText.setText(winner + " you won");
        primaryStage.setScene(winScene);
        primaryStage.show();
    }

    private Parent buildWinScreen() {
        this.setPrefSize(700, 800);

        winnerText = new Label();
        winnerText.setTranslateX(200);
        winnerText.setTranslateY(150);
        winnerText.setFont(Font.font(20));


        Button newGameButton = new Button("Start");
        newGameButton.setTranslateX(400);
        newGameButton.setTranslateY(400);
        newGameButton.setOnKeyPressed(event -> controller.buildView());
        newGameButton.setOnMouseClicked(event -> controller.buildView());
        this.getChildren().addAll(winnerText, newGameButton);
        return this;
    }
}
*/
