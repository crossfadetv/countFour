package sample;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class Main extends Application {

    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    private static final int FIELDSIZE = 100;
    private Stone[][] stoneContainerGrid = new Stone[COLUMNS][ROWS];
    private Pane stonePane = new Pane();
    private Scene playScene;
    private Stage primaryStage;
    private ArrayList<Player> players;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        playScene = createScene();
        primaryStage.setScene(playScene);
        Scene entryScreen = createEntryScreen();
        primaryStage.setScene(entryScreen);
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
            positionArrow.setOnMouseClicked(event -> throwStone(column, getPlayerOnTurn()));
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

    private Polygon createArrow() {
        double[] points = {60, 90, 30, 50, 45, 50, 45, 30, 75, 30, 75, 50, 90, 50};
        Polygon arrow = new Polygon(points);
        arrow.setFill(Color.GREEN);
        return arrow;
    }

    private void throwStone(int column, Player player) {
        column = column - 1;

        for (int row = ROWS - 1; row >= 0; row--) {
            if (stoneContainerGrid[column][row] == null && row<=5) {
                Stone stone = player.playStone();
                stoneContainerGrid[column][row] = stone;
                stone.setTranslateX(column * FIELDSIZE + 50); //hässlich mit dem 50
                stone.setTranslateY(row * FIELDSIZE + 150); //hässlich mit dem 150
                stonePane.getChildren().add(stone);
                System.out.println(column+" "+ row);
                changePlayerTurn();
                return;
            } else {
            }
        }
    }


    public Scene createEntryScreen() {
        Pane entryRoot = new GridPane();
        entryRoot.setPrefSize(700, 700);

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
        startGameButton.setOnMouseClicked(event -> startGame(redPlayer.getText(), yellowPlayer.getText()));
        entryRoot.getChildren().addAll(newGameText, redLabel, redPlayer, yellowPlayer, yellowLabel, startGameButton);
        Scene entryScreen = new Scene(entryRoot);
        return entryScreen;

    }

    public void startGame(String redPlayerName, String yellowPlayerName){
        Player redPlayer = new Player(redPlayerName, Color.RED, true);
        Player yellowPlayer = new Player(yellowPlayerName, Color.YELLOW, false);
        players = new ArrayList<>();
        players.add(redPlayer);
        players.add(yellowPlayer);
        primaryStage.setScene(playScene);
    }

    public Player getPlayerOnTurn(){
        Player playerOnTurn =null;
    for(Player player: players){
       if(player.onTurn()){
           playerOnTurn = player;
       }
    }
       return playerOnTurn;
    }

    public void changePlayerTurn(){
        for(Player player: players){
            player.changeTurn();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

