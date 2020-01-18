package countFour.view;

import countFour.Controller;
import countFour.model.Game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlayScreen extends GridPane {
    private static final int COLUMNS = Game.COLUMNS;
    private static final int ROWS = Game.ROWS;
    private static final int FIELDSIZE = Game.FIELDSIZE;

    private Stage primaryStage;
    private Scene playScene;
    private Pane stonePane = new Pane();
    private Label infoBox;
    private VBox infoBoxContainer;
    private Controller controller;
    private ArrayList<Polygon> positionArrows;

    private boolean muteAudio = false;

    public PlayScreen(Controller controller, Stage primaryStage) {
        super();
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.positionArrows = new ArrayList<>();
        this.playScene = new Scene(buildPlayScreen());

    }


    public Parent buildPlayScreen() {
        this.getChildren().add(stonePane);
        stonePane.setId("stone-pane");
        this.setPrefSize(700, 800);

        for (int x = 0; x <= COLUMNS-1; x++) {
            Polygon positionArrow = createArrow();
            positionArrow.setTranslateX(((x+1) * FIELDSIZE) - 80);
            positionArrow.setOnMouseEntered(event -> positionArrow.setCursor(Cursor.HAND));
            positionArrows.add(positionArrow);
            final int column = x;
            positionArrow.setOnMouseClicked(event -> makeMove(column));
            this.getChildren().add(positionArrow);
            for (int y = 1; y <= ROWS; y++) {
                Rectangle rectangle = new Rectangle(x  * FIELDSIZE, y * FIELDSIZE, 100, 100);
                rectangle.setFill(Color.NAVY);
                Circle circle = new Circle(x  * FIELDSIZE + FIELDSIZE / 2, y * FIELDSIZE + FIELDSIZE / 2, FIELDSIZE / 2 - 10);
                Shape field = Shape.subtract(rectangle, circle);
                field.setFill(Color.NAVY);
                field.setTranslateX((x * 100));
                field.setTranslateY((y * 100));
                this.getChildren().add(field);
            }

            this.getChildren().add(getInfoBox());

        }
        return this;
    }

    private Polygon createArrow() {
        double[] points = {60, 90, 30, 50, 45, 50, 45, 30, 75, 30, 75, 50, 90, 50};
        Polygon arrow = new Polygon(points);
        arrow.setFill(Color.GREEN);
        return arrow;
    }

    private void disablePositionArrows() {
        for (Polygon positionArrow : positionArrows) {
            positionArrow.setDisable(true);
            positionArrow.setFill(Color.GRAY);
        }
    }

    public void enablePositionArrows() {
        for (Polygon positionArrow : positionArrows) {
            positionArrow.setDisable(false);
            positionArrow.setFill(Color.GREEN);
        }
    }


    private VBox getInfoBox() {

        infoBox = new Label();
        infoBoxContainer = new VBox(infoBox);
        infoBoxContainer.setAlignment(Pos.CENTER);
        infoBoxContainer.setPrefSize(700, 100);
        infoBoxContainer.setTranslateY(700);
        return infoBoxContainer;
    }


    public void showScreen() {
        playScene.getStylesheets().add(getClass().getResource("PlayScreen.css").toExternalForm());
        infoBox.setId("red-notification");
        infoBox.setText(controller.showPlayer().toUpperCase() + ", du bist am Zug!");
        primaryStage.setScene(playScene);
        primaryStage.show();
    }

    private void makeMove(int column) {
        try {
            Circle stone = controller.handlePlayMove(column);
            stonePane.getChildren().add(stone);
            playAnimation(column, stone);

            if (!controller.hasGameEnded()) {
                if (controller.getGame().getPlayerOnTurn().getColor().equals(Color.RED)) {
                    infoBox.setId("red-notification");
                } else {
                    infoBox.setId("yellow-notification");
                }
                infoBox.setText(controller.showPlayer() + ", du bist am Zug!");
            } else {
                controller.handleEndGame();
                disablePositionArrows();
            }
        } catch (NullPointerException e) {
            infoBox.setText(controller.showPlayer() + ", du bist am Zug!" + "\n" + "Wähle eine andere Reihe!");
        }
    }

    public void resetStonePane() {
        stonePane.getChildren().clear();
    }

    public void addWinnerInfo() {
        //Audio Setup
        if (!muteAudio) {
            double audioDelay = 700;
            ScheduledExecutorService scheduler
                    = Executors.newSingleThreadScheduledExecutor();

            Runnable task = () -> {
                Media winnerSound = new Media(new File("src/countFour/view/audio/win.mp3").toURI().toString());
                MediaPlayer playWinnerSound = new MediaPlayer(winnerSound);
                playWinnerSound.setAutoPlay(true);
                playWinnerSound.play();
            };
            int delay = (int) audioDelay;
            scheduler.schedule(task, delay, TimeUnit.MILLISECONDS);

        }
        infoBox.setText(controller.getWinner() + " ,du hast gewonnen!");
        addNewGameButton();

    }

    public void addDrawInfo() {
        infoBox.setText("it's a draw");
        addNewGameButton();
    }

    public void resetWinnerText() {
        infoBoxContainer.getChildren().clear();
        infoBoxContainer.getChildren().add(infoBox);
    }

    public void addNewGameButton() {
        Button newGame = new Button("New Game");
        newGame.setOnMouseClicked(event -> controller.handleNewGame());
        infoBoxContainer.getChildren().add(newGame);
    }

    public void playAnimation(int column, Circle stone){

        //Animation Setup
        double dropDuration = 500;
        //Audio Setup
        if (!this.muteAudio) {

            ScheduledExecutorService scheduler
                    = Executors.newSingleThreadScheduledExecutor();

            Runnable task = () -> {
                Media drop = new Media(new File("src/countFour/view/audio/drop.mp3").toURI().toString());
                MediaPlayer playDrop = new MediaPlayer(drop);
                playDrop.setAutoPlay(true);
                playDrop.play();
            };
            int delay = (int) dropDuration-50;
            scheduler.schedule(task, delay, TimeUnit.MILLISECONDS);
            //scheduler.shutdown();
        }


        //Set Start Position
        stone.setTranslateX(column * FIELDSIZE + 50);
        stone.setTranslateY(50);
        //Set End Position
        KeyFrame startPos = new KeyFrame(Duration.millis(dropDuration),
                new KeyValue(stone.translateXProperty(),column*FIELDSIZE+50),
                new KeyValue(stone.translateYProperty(),controller.getLastPlayedRow() * FIELDSIZE + 150));
        //Invoke Animation
        Timeline tl = new Timeline();
        tl.getKeyFrames().addAll(startPos);
        tl.setCycleCount(1);
        tl.play();
    }


    public boolean getMuteAudio() {
        return muteAudio;
    }

    public void setMuteAudio() {
        this.muteAudio=!this.muteAudio;
    }
}