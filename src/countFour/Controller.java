package countFour;

import countFour.model.Game;
import countFour.model.Stone;
import countFour.view.EntryScreen;
import countFour.view.PlayScreen;
//import countFour.view.WinScreen;

public class Controller {
    private EntryScreen entryScreen;
    private PlayScreen playScreen;
  //  private WinScreen winScreen;
    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public void buildView() {
        entryScreen.showScreen();
    }


    public Stone handlePlayMove(int column) {
        return game.playMove(column);
    }

    public boolean hasGameEnded() {
        return game.getHasGameEnded();
    }

    public void handleStartGame(String redPlayerName, String yellowPlayerName) {
        game.startGame(redPlayerName, yellowPlayerName);
        playScreen.showScreen();
    }

    public void handleEndGame() {
        playScreen.addWinnerInfo();
    }

    public String getWinner() {
        return game.getPlayerOnTurn().getName();
    }

    public void setEntryScreen(EntryScreen entryscreen) {
        this.entryScreen = entryscreen;
    }

    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    /*public void setWinScreen(WinScreen winScreen) {
        this.winScreen = winScreen;
    }*/


    public String showPlayer() {
        return game.getPlayerOnTurn().getName();
    }

    public void handleNewGame(){
        playScreen.resetStonePane();
        playScreen.resetWinnerText();
        playScreen.enablePositionArrows();
        this.game = new Game();
        buildView();
    }


}
