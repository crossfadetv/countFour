package countFour.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.Map;

public class EntryScreen extends Pane {
    //TODO: define controller

    public EntryScreen(){
        super();
    }

    public Parent buildEntryScreen(){
        this.setPrefSize(700, 700);

        Label newGameText = new Label("NEW GAME");
        newGameText.setTranslateX(200);
        newGameText.setTranslateY(150);
        newGameText.setFont(Font.font(20));

        Label redLabel = new Label("RED Player");
        redLabel.setTranslateX(100);
        redLabel.setTranslateY(300);
        TextField redPlayer = new TextField();
        redPlayer.setTranslateX(200);
        redPlayer.setTranslateY(300);
        Label yellowLabel = new Label("YELLOW Player");
        yellowLabel.setTranslateX(100);
        yellowLabel.setTranslateY(350);
        TextField yellowPlayer = new TextField();
        yellowPlayer.setTranslateX(200);
        yellowPlayer.setTranslateY(350);

        Button startGameButton = new Button("Start");
        startGameButton.setTranslateX(400);
        startGameButton.setTranslateY(400);
    // startGameButton.setOnKeyPressed(event -> controller.startGame(redPlayer.getText(), yellowPlayer.getText()));
     //startGameButton.setOnMouseClicked(event -> controller.startGame(redPlayer.getText(), yellowPlayer.getText()));
        this.getChildren().addAll(newGameText, redLabel, redPlayer, yellowPlayer, yellowLabel, startGameButton);
        return this;
    }
}
