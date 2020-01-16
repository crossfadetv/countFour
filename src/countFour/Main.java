package countFour;

import countFour.model.Game;
import countFour.view.EntryScreen;
import countFour.view.PlayScreen;
//import countFour.view.WinScreen;
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

