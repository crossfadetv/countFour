package countFour.view;

import countFour.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EntryScreen extends VBox {
    private Controller controller;
    private Stage primaryStage;
    private Scene entryScene;

    public EntryScreen(Controller controller, Stage primaryStage) {
        super();
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.entryScene = new Scene(buildEntryScreen());
        primaryStage.setMinWidth(715);
        primaryStage.setMaxWidth(715);
    }

    public void showScreen() {
        primaryStage.setScene(entryScene);
        entryScene.getStylesheets().add(getClass().getResource("EntryScreen.css").toExternalForm());
        primaryStage.show();
        primaryStage.setTitle("VIER GEWINNT");
    }

    //get the Banner Img File
    public FileInputStream getImage() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/countFour/view/bannerEntryScreen.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fis;
    }

    private Parent buildEntryScreen() {
        int windowWidth = 700;
        int windowHeight = 400;
        this.setPrefSize(windowWidth, windowHeight);


        //create Banner Img
        Image bannerImage = new Image(getImage());
        ImageView showBanner = new ImageView(bannerImage);
        showBanner.setFitWidth(windowWidth);
        showBanner.setPreserveRatio(true);
        HBox banner = new HBox(showBanner);
        banner.setAlignment(Pos.TOP_CENTER);

        //Create Input Form
        HBox form = new HBox();
        form.setAlignment(Pos.TOP_CENTER);
        GridPane grid = new GridPane();
        Label instruction = new Label ("Gebt eure Namen ein:");
        TextField redPlayer = new TextField();
        redPlayer.setId("redfield");
        TextField yellowPlayer = new TextField();
        yellowPlayer.setId("yellowfield");
        Button continueGameButton = new Button("letztes Spiel fortsetzen");
        continueGameButton.setId("continue-btn");
        grid.add(instruction,0,0);
        grid.add(redPlayer,0,1);
        grid.add(yellowPlayer,0,2);
        grid.add(continueGameButton,0,3);
        grid.setVgap(10);
        grid.setHgap(5);
        grid.setGridLinesVisible(false);
        grid.setAlignment(Pos.CENTER);
        grid.setMargin(continueGameButton, new Insets(20,0,0,0));

        Button startGameButton = new Button("Start!");
        startGameButton.setId("start-btn");
        startGameButton.setOnMouseEntered(event -> startGameButton.setCursor(Cursor.HAND));

        form.getChildren().addAll(grid,startGameButton);
        form.setMargin(startGameButton, new Insets(0,0,25,20));
        form.setAlignment(Pos.CENTER);


        this.setMargin(form, new Insets(40, 0, 0, 0));

        ObservableList list = this.getChildren();

        list.addAll(banner,form);

        //set Start Btn Event
        startGameButton.setOnKeyPressed(event -> controller.handleStartGame(redPlayer.getText(), yellowPlayer.getText()));
        startGameButton.setOnMouseClicked(event -> controller.handleStartGame(redPlayer.getText(), yellowPlayer.getText()));

        return this;
    }
}
