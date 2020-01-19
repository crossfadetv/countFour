package countFour;

import countFour.model.Game;
import countFour.model.SavedGame;
import countFour.model.Stone;
import countFour.view.EntryScreen;
import countFour.view.PlayScreen;
//import countFour.view.WinScreen;

public class Controller {
    private EntryScreen entryScreen;
    private PlayScreen playScreen;
    private Game game;

    /**
     * @param game defines game
     */
    public Controller(Game game) {
        this.game = game;
    }

    /**
     * let's entryscreen display the screen
     */
    public void buildView() {
        entryScreen.showScreen();
    }

    /**
     * @return the current game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param column the column in which the stone should be played
     * @return if the column is not full the played stone is returned
     * else null is returned.
     */
    public Stone handlePlayMove(int column) {
        return game.playMove(column);
    }

    /**
     * @return whether or not game has ended
     */
    public boolean hasGameEnded() {
        return game.getHasGameEnded();
    }

    /**
     * handles start of the game and let's playscreen be displayed
     *
     * @param redPlayerName    name of red player
     * @param yellowPlayerName name of yellow player
     */
    public void handleStartGame(String redPlayerName, String yellowPlayerName) {
        game.startGame(redPlayerName, yellowPlayerName);
        playScreen.showScreen();
    }

    /**
     * restore the last game state
     */
    public void handleContinueGame() {
        SavedGame savedGame = new SavedGame();
        savedGame.loadGame();
        int restoreTurnAmount = savedGame.getTurns().size();
        game.startGame(savedGame.getRedPlayerName(),savedGame.getYellowPlayerName());
        playScreen.toggleAudio();
        playScreen.showScreen();
        playScreen.restoreSavedGame(savedGame);
        for (int i=game.getSavedGame().getTurns().size();i>restoreTurnAmount;i--) {
            game.getSavedGame().getTurns().remove(i);
        }
        playScreen.toggleAudio();



    }

    /**
     * handles the end of the game in case of draw or win.
     */
    public void handleEndGame() {
        if (game.getIsDraw()) {
            playScreen.addDrawInfo();
        } else {
            playScreen.addWinnerInfo();
        }
    }

    /**
     * @return name of the winner
     */
    public String getWinner() {
        return game.getPlayerOnTurn().getName();
    }

    /**
     * defines entry screen
     */
    public void setEntryScreen(EntryScreen entryscreen) {
        this.entryScreen = entryscreen;
    }

    /**
     * defines play screen
     */
    public void setPlayScreen(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    /**
     * @return the name of the player on turn
     */
    public String showPlayer() {
        return game.getPlayerOnTurn().getName();
    }

    /**
     * handles a new game (resets play screen and creates new game)
     */
    public void handleNewGame() {
        playScreen.resetStonePane();
        playScreen.resetWinnerText();
        playScreen.enablePositionArrows();
        this.game = new Game();
        buildView();
    }

    /**
     * @return the row in which the latest stone was played
     */
    public int getLastPlayedRow() {
        return game.getLastPlayedRow();
    }

}
