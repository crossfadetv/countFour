package countFour;

import countFour.model.Game;
import countFour.model.Stone;
import countFour.view.EntryScreen;
import countFour.view.PlayScreen;

public class Controller {
    private EntryScreen entryScreen;
    private PlayScreen playScreen;
    private Game game;

//TODO:
    public Controller(Game game){
        this.game = game;
    }

    public void buildView(){
        entryScreen.showScreen();
    }

    public Stone handlePlayMove(int column) {
        return game.playMove(column);
    }

        public void handleStartGame(String redPlayerName, String yellowPlayerName){
        game.startGame(redPlayerName, yellowPlayerName);
        playScreen.showScreen();
        }

        public void setEntryScreen(EntryScreen entryscreen){
        this.entryScreen = entryscreen;
        }

        public void setPlayScreen(PlayScreen playScreen){
        this.playScreen = playScreen;
        }




}
