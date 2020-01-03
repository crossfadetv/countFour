package countFour;

import countFour.model.Game;
import countFour.model.Stone;
import countFour.view.EntryScreen;
import countFour.view.PlayScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
    private Stage primaryStage;
    private Game game;

    public Controller(Game game){
        this.game = game;
    }

    public void buildView(Stage primaryStage){
        this.primaryStage = primaryStage;
        Scene playScene = new Scene(new PlayScreen().buildPlayScreen());
        primaryStage.setScene(playScene);
        Scene entryScreen = new Scene(new EntryScreen().buildEntryScreen());
        primaryStage.setScene(entryScreen);
        primaryStage.setTitle("4 Gewinnt");
        primaryStage.show();
    }

    public Stone handleThrowStone(int column) {
        return game.throwStone(column);
        }




}
