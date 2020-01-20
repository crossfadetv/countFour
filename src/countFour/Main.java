/**
 * This javaFX application implements a count four game. It allows two players to register their names and
 * play moves. There is a validation function that evaluates the game status (check for winner). An Arraylist
 * that stores the turns is written to a separate file to allow players restoring an unfinished game.
 *
 *
 * @author  Rahel Krubally, Markus Steiner
 * @version 1.0
 * @since   2019-12-1
 */
package countFour;

import countFour.model.Game;
import countFour.view.EntryScreen;
import countFour.view.PlayScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private Controller controller;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = new Game();
        controller = new Controller(game);
        controller.setPlayScreen(new PlayScreen(controller, primaryStage));
        controller.setEntryScreen(new EntryScreen(controller, primaryStage));
        controller.buildView();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

