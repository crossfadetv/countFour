/**
 * This class implements the user interface (javaFX) and its layout for the games's entry screen.
 *
 *
 * @author  Rahel Krubally, Markus Steiner
 * @version 1.0
 * @since   2019-12-01
 */
package countFour.view;

import countFour.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private TextField redPlayer;
    private TextField yellowPlayer;

    /**
     * @param controller defines controller
     * @param primaryStage defines primaryStage
     * */
    public EntryScreen(Controller controller, Stage primaryStage) {
        super();
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.entryScene = new Scene(buildEntryScreen());
        primaryStage.setMinWidth(715);
        primaryStage.setMaxWidth(715);
    }

    /**
     * displays the entry screen
     * */
    public void showScreen() {
        primaryStage.setScene(entryScene);
        entryScene.getStylesheets().add(getClass().getResource("EntryScreen.css").toExternalForm());
        primaryStage.show();
        primaryStage.setTitle("VIER GEWINNT");
    }

    //get the Banner Img File
    private FileInputStream getImage() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/countFour/view/img/bannerEntryScreen.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fis;
    }

    private Parent buildEntryScreen() {
        int windowWidth = 700;
        int windowHeight = 400;
        this.setPrefSize(windowWidth, windowHeight);

        HBox banner = createBannerImage(windowWidth);
        HBox form = createForm();

        this.setMargin(form, new Insets(40, 0, 0, 0));
        ObservableList list = this.getChildren();
        list.addAll(banner,form);
        return this;
    }

    private HBox createBannerImage(int windowWidth){
        Image bannerImage = new Image(getImage());
        ImageView showBanner = new ImageView(bannerImage);
        showBanner.setFitWidth(windowWidth);
        showBanner.setPreserveRatio(true);
        HBox banner = new HBox(showBanner);
        banner.setAlignment(Pos.TOP_CENTER);
        return banner;
    }

    private GridPane createInputForm(){
        GridPane grid = new GridPane();
        Label instruction = new Label ("Gebt eure Namen ein:");
        redPlayer = new TextField();
        redPlayer.setId("redfield");
        yellowPlayer = new TextField();
        yellowPlayer.setId("yellowfield");
        Button continueGameButton = new Button("letztes Spiel fortsetzen");
        continueGameButton.setOnMouseClicked(event -> continueGame());
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
        return grid;
    }

    private HBox createForm() {
        HBox form = new HBox();
        form.setAlignment(Pos.TOP_CENTER);
        Button startGameButton = new Button("Start!");
        startGameButton.setOnMouseEntered(event -> startGameButton.setCursor(Cursor.HAND));
        startGameButton.setOnKeyPressed(event -> tryStartGame(redPlayer.getText(), yellowPlayer.getText()));
        startGameButton.setOnMouseClicked(event -> tryStartGame(redPlayer.getText(), yellowPlayer.getText()));
        form.getChildren().addAll(createInputForm(),startGameButton);
        startGameButton.setId("start-btn");
        form.setMargin(startGameButton, new Insets(0,0,25,20));
        form.setAlignment(Pos.CENTER);
        return form;
    }

     private void tryStartGame(String redPlayer, String yellowPlayer){
        if(redPlayer.isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Wie heisst der rote Spieler");
            errorAlert.setContentText("Bitte einen Namen eingeben");
            errorAlert.showAndWait();
        }
        else if(yellowPlayer.isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Wie heisst der gelbe Spieler");
            errorAlert.setContentText("Bitte einen Namen eingeben");
            errorAlert.showAndWait();
        }
        else{
            controller.handleStartGame(redPlayer, yellowPlayer);
        }

    }
    private void continueGame() {
        controller.handleContinueGame();
    }
}
