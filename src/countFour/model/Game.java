/**
 * This class implements the game functionality like setting up or restoring a game,
 * and validates the grid.
 *
 *
 * @author  Rahel Krubally
 * @version 1.0
 * @since   2019-12-1
 */
package countFour.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Observable;


public class Game extends Observable {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final int FIELDSIZE = 100;
    private ArrayList<Player> players;
    private Stone[][] stoneContainerGrid = new Stone[COLUMNS][ROWS];
    private int lastPlayedRow;
    private boolean hasGameEnded = false;
    private boolean isDraw = false;
    //private boolean muteAudio = false;
    private SavedGame savedGame = new SavedGame();

    /**
    * @return ArrayList containing the players
    * */
    public ArrayList getPlayers() {
        return players;
    }


    /**
     * plays a stone to a free row if possible
     * @param column the column in which the stone should be played
     * @return if the column is not full the played stone is returned
     *         else null is returned.
     */
    public Stone playMove(int column) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (stoneContainerGrid[column][row] == null /*&& row <= 5*/) {
                Stone stone = getPlayerOnTurn().playStone();
                stoneContainerGrid[column][row] = stone;
                lastPlayedRow = row ;
                System.out.println(column + " " + row);
                checkForWinner(getPlayerOnTurn(), column, row);
                if (!getHasGameEnded()) {
                    changePlayerTurn();
                }
                savedGame.addTurn(column);
                savedGame.saveGame(SavedGame.getPATH());
                return stone;
            } else {
            }
        }
        return null;
    }

    /**
     * @return the player that is on turn
     * */
    public Player getPlayerOnTurn() {
        Player playerOnTurn = null;
        for (Player player : players) {
            if (player.onTurn()) {
                playerOnTurn = player;
            }
        }
        return playerOnTurn;
    }

    /**
     * changes the player on turn
     * */
    public void changePlayerTurn() {
        for (Player player : players) {
            player.changeTurn();
        }
    }

    /**
     * checks for winning combinations of four vertically, horizontally and diagonally
     * @param player the player that played the stone
     * @param column the column to which the stone was thrown
     * @param row the row in which the stone was thrown
     */
    public void checkForWinner(Player player, int column, int row) {
        int counter = 0;
        Color currentColor = player.getColor();
        //check vertical wins
        for (int x = row; x <= row + 3; x++) {
            if (x < ROWS && stoneContainerGrid[column][x] != null && stoneContainerGrid[column][x].getColor() == currentColor) {
                counter++;
                if (counter > 3) {
                    System.out.println(player.getName() + " has won");
                    setHasGameEnded(true);
                    return;
                }
            } else {
                counter = 0;
            }
        }
        counter =0;
        //check horizontal wins
        for (int x = 0; x < COLUMNS; x++) {
            if (stoneContainerGrid[x][row] != null && stoneContainerGrid[x][row].getColor() == currentColor) {
                counter++;
                if (counter > 3) {
                    System.out.println(player.getName() + " has won");
                    setHasGameEnded(true);
                    return;
                }
            } else {
                counter = 0;
            }
        }

        //check diagonal wins
        //descending
        for (int x = 0; x < COLUMNS - 3; x++) {
            for (int y = 0; y < ROWS - 3; y++) {
                for (int offset = 1; offset <= 3; ) {
                    if (stoneContainerGrid[x][y] != null && stoneContainerGrid[x + offset][y + offset] != null && stoneContainerGrid[x][y].getColor() == stoneContainerGrid[x + offset][y + offset].getColor()) {
                        offset++;
                        if (offset > 3) {
                            System.out.println(player.getName() + " has won");
                            setHasGameEnded(true);
                            return;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        //ascending diagonals
        for (int x = 0; x < COLUMNS - 3; x++) {
            for (int y = 3; y < ROWS; y++) {
                for (int offset = 1; offset <= 3; ) {
                    if (stoneContainerGrid[x][y] != null && stoneContainerGrid[x + offset][y - offset] != null && stoneContainerGrid[x][y].getColor() == stoneContainerGrid[x + offset][y - offset].getColor()) {
                        offset++;
                        if (offset > 3) {
                            System.out.println(player.getName() + " has won");
                            setHasGameEnded(true);
                            return;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        //check for draw
        int draw = 0;
        for(Player p : players){
            draw += p.countStones();
        }
        if(draw == 0){
            System.out.println("draw");
            setHasGameEnded(true);
            setIsDraw(true);
        }
    }

    /**
     * starts the game and instantiates players
     * @param redPlayerName name of red player
     * @param yellowPlayerName name of yellow player
     * */
    public void startGame(String redPlayerName, String yellowPlayerName) {
        Player redPlayer = new Player(redPlayerName, Color.RED, true);
        Player yellowPlayer = new Player(yellowPlayerName, Color.YELLOW, false);
        players = new ArrayList<>();
        players.add(redPlayer);
        players.add(yellowPlayer);
        savedGame.setRedPlayerName(redPlayerName);
        savedGame.setYellowPlayerName(yellowPlayerName);
    }
    public void continueGame() {
        savedGame.loadGame(SavedGame.getPATH());
        this.startGame(savedGame.getRedPlayerName(),savedGame.getYellowPlayerName());
    }


    private void setHasGameEnded(boolean hasGameEnded) {
        this.hasGameEnded = hasGameEnded;
    }

    /**
     * @return whether the game has ended or not
     *  */
    public boolean getHasGameEnded() {
        return hasGameEnded;
    }

    private void setIsDraw(boolean isDraw){
        this.isDraw = isDraw;
    }

    /**
     * @return whether the game ended in a draw or not
     * */
    public boolean getIsDraw(){
        return isDraw;
    }

    /**
     * @return the last played row
     * */
    public int getLastPlayedRow() {
        return lastPlayedRow;
    }

    /**
     *
     * @return the SaveGame object
     */
    public SavedGame getSavedGame() {
        return savedGame;
    }
}
