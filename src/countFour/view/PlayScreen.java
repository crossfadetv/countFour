package countFour.view;

import countFour.Controller;
import countFour.model.Game;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PlayScreen extends GridPane {
    //TODO: discuss where to define.
    private static final int COLUMNS = Game.COLUMNS;
    private static final int ROWS = Game.ROWS;
    private static final int FIELDSIZE = Game.FIELDSIZE;

    private Stage primaryStage;
    private Scene playScene;
    private Pane stonePane = new Pane();
    private TextArea infoBox;
    private VBox infoBoxContainer;
    private Controller controller;
    private ArrayList<Polygon> positionArrows;

    public PlayScreen(Controller controller, Stage primaryStage) {
        super();
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.positionArrows = new ArrayList<>();
        this.playScene = new Scene(buildPlayScreen());

    }


    public Parent buildPlayScreen() {
        this.getChildren().add(stonePane);
        this.setPrefSize(700, 800);

        for (int x = 1; x <= COLUMNS; x++) {
            Polygon positionArrow = createArrow();
            positionArrow.setTranslateX((x * FIELDSIZE) - 80);
            positionArrow.setOnMouseEntered(event -> positionArrow.setCursor(Cursor.HAND));
            positionArrows.add(positionArrow);
            final int column = x;
            positionArrow.setOnMouseClicked(event -> makeMove(column));
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
        infoBox = new TextArea();
        infoBoxContainer = new VBox(infoBox);
        infoBoxContainer.setPrefSize(700, 100);
        infoBoxContainer.setTranslateX(0);
        infoBoxContainer.setTranslateY(700);
        return infoBoxContainer;
    }


    public void showScreen() {
        infoBox.setText(controller.showPlayer() + " it's your turn!");
        primaryStage.setScene(playScene);
        primaryStage.show();

    }

    private void makeMove(int column) {
        try {
            stonePane.getChildren().add(controller.handlePlayMove(column));
            if (!controller.hasGameEnded()) {
                infoBox.setText(controller.showPlayer() + " it's your turn!");
            } else {
                controller.handleEndGame();
                disablePositionArrows();
            }
        } catch (NullPointerException e) {
            infoBox.setText(controller.showPlayer() + " it's your turn!" + "\n" + "Column is full!");
        }
    }

    public void resetStonePane() {
        stonePane.getChildren().clear();
    }

    public void addWinnerInfo() {
        infoBox.setText(controller.getWinner() + " you are the winner");
        Button newGame = new Button("New Game");
        newGame.setOnMouseClicked(event -> controller.handleNewGame());
        infoBoxContainer.getChildren().add(newGame);
    }

    public void resetWinnerText() {
        infoBoxContainer.getChildren().clear();
        infoBoxContainer.getChildren().add(infoBox);
    }


}
